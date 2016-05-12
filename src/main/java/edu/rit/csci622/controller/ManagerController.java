package edu.rit.csci622.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.rit.csci622.auth.Auth;
import edu.rit.csci622.auth.Role;
import edu.rit.csci622.data.PasswordHandler;
import edu.rit.csci622.data.Util;
import edu.rit.csci622.data.dao.GeneralDao;
import edu.rit.csci622.data.dao.impl.GeneralDaoImpl;
import edu.rit.csci622.data.dao.impl.ManagerDaoImpl;
import edu.rit.csci622.model.Hire;
import edu.rit.csci622.model.Product;
import edu.rit.csci622.model.User;

/**
 * All manager based operation are implemented here.
 * 
 * @author Nathaniel Cotton
 *
 */
@org.springframework.stereotype.Controller
@Auth(roles = { Role.MANAGER })
@RequestMapping("/manage")
public class ManagerController extends Controller {

	@Autowired
	private HttpServletRequest request;

	/**
	 * Shows all the products that are currently in the system, both active and inactive.
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) throws IOException {
		GeneralDao dao = new GeneralDaoImpl();
		List<Product> products = dao.getProducts(PasswordHandler.getDbPassword());

		model.addAttribute("products", Util.filterProductsForNow(products));
		model.addAttribute("product", new Product());
		return "manage";
	}

	/**
	 * Allows for the creation of a product.
	 * 
	 * @param product
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/createproduct", method = RequestMethod.POST)
	public String createProduct(Product product) throws IOException {
		if (product.getAmount() > 0) {
			ManagerDaoImpl mdi = new ManagerDaoImpl();
			mdi.createProduct(product.getName(), product.getDescription(), product.isActive(), product.getAmount(),
					getUser(request).getIdUser(), PasswordHandler.getDbPassword());
		}
		return "redirect:/manage";
	}

	/**
	 * Allows for a product to be activated or deactivated.
	 * 
	 * @param prodId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/flipprod", method = RequestMethod.POST)
	public String flipProd(int prodId) throws IOException {
		ManagerDaoImpl mdi = new ManagerDaoImpl();
		List<Product> p = mdi.getProduct(prodId, PasswordHandler.getDbPassword());
		if (p != null) {
			Product prod = p.get(0);
			mdi.updateProduct(prod.getIdProduct(), prod.getName(), prod.getDescription(), !prod.isActive(),
					PasswordHandler.getDbPassword());
		}
		return "redirect:/manage";
	}

	/**
	 * Allows for a products price to change
	 * 
	 * @param prodId
	 * @param price
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/changeprice", method = RequestMethod.POST)
	public String changePrice(int prodId, int price) throws IOException {
		User u = getUser(request);
		ManagerDaoImpl mdi = new ManagerDaoImpl();
		mdi.updatePrice(price, u.getIdUser(), prodId);
		return "redirect:/manage";
	}

	/**
	 * Allows for a products name and or description to be changed.
	 * 
	 * @param prod
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/updateproduct", method = RequestMethod.POST)
	public String updateProduct(Product prod) throws IOException {
		ManagerDaoImpl dao = new ManagerDaoImpl();
		Product p = Util.getMostRecent(dao.getProduct(prod.getIdProduct(), PasswordHandler.getDbPassword()));
		dao.updateProduct(p.getIdProduct(), prod.getName(), prod.getDescription(), p.isActive(),
				PasswordHandler.getDbPassword());
		return "redirect:/manage";
	}

	/**
	 * Allows for a manager to view hire requests if they haven't already been signed off on.
	 * 
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/hire", method = RequestMethod.GET)
	public String hire(Model model) throws IOException {
		ManagerDaoImpl dao = new ManagerDaoImpl();
		List<Hire> hireRequests = Util.filterManagerSignOff(dao.getHire());
		HashMap<Integer, List<User>> requests = new HashMap<Integer, List<User>>();
		requests.put(Role.MANAGER.roleId, new ArrayList<User>());
		requests.put(Role.HR.roleId, new ArrayList<User>());
		requests.put(Role.EMPLOYEE.roleId, new ArrayList<User>());
		for (Hire h : hireRequests) {
			if (requests.containsKey(h.getRequestedRole()))
				requests.get(h.getRequestedRole()).add(dao.getUser(h.getIdUser(), PasswordHandler.getDbPassword()));
		}
		model.addAttribute("managerRequest", requests.get(Role.MANAGER.roleId));
		model.addAttribute("hrRequest", requests.get(Role.HR.roleId));
		model.addAttribute("employeeRequest", requests.get(Role.EMPLOYEE.roleId));
		return "hire";
	}

	/**
	 * Allows the manager to sign off on a hire.
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/hire", method = RequestMethod.POST)
	public String hire(int userId, int roleId) throws IOException {
		ManagerDaoImpl dao = new ManagerDaoImpl();
		User u = getUser(request);
		dao.managerHireSignOff(userId, u.getIdUser(), roleId);
		return "redirect:/manage/hire";
	}

	/**
	 * Brings up a page of all the employees that are firable.
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/fire", method = RequestMethod.GET)
	public String fire(Model model) throws IOException {
		User u = getUser(request);
		ManagerDaoImpl dao = new ManagerDaoImpl();
		List<User> users = Util.filterForEmployees(dao.getUsers(PasswordHandler.getDbPassword()));
		Iterator<User> it = users.iterator();
		while (it.hasNext()) {
			User t = it.next();
			if (u.getIdUser() == t.getIdUser())
				it.remove();
		}
		model.addAttribute("emp", users);
		return "fire";
	}

	/**
	 * Allows the manager to fire any currently hired employee (HR,Manager,Employee).
	 * 
	 * @param userId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/fire", method = RequestMethod.POST)
	public String fire(int userId) throws IOException {
		ManagerDaoImpl dao = new ManagerDaoImpl();
		dao.fireEmployee(userId);
		return "redirect:/manage/fire";
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}
