package edu.rit.csci622.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.rit.csci622.auth.Auth;
import edu.rit.csci622.auth.Role;
import edu.rit.csci622.data.PasswordHandler;
import edu.rit.csci622.data.dao.GeneralDao;
import edu.rit.csci622.data.dao.impl.GeneralDaoImpl;
import edu.rit.csci622.data.dao.impl.ManagerDaoImpl;
import edu.rit.csci622.model.Product;
import edu.rit.csci622.model.User;

@org.springframework.stereotype.Controller
@Auth(roles = { Role.MANAGER })
@RequestMapping("/manager")
public class ManagerController extends Controller {

	@Autowired
	private HttpServletRequest request;

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) throws IOException {
		GeneralDao dao = new GeneralDaoImpl();
		List<Product> products = dao.getProducts(PasswordHandler.getDbPassword());
		model.addAttribute("products", products);
		return "manage";
	}

	@RequestMapping(value = "/createproduct", method = RequestMethod.POST)
	public String createProduct(Product product, int price) throws IOException {
		if (price > 0) {
			ManagerDaoImpl mdi = new ManagerDaoImpl();
			mdi.createProduct(product.getName(), product.getDescription(), product.isActive(), price,
					getUser(request).getIdUser(), PasswordHandler.getDbPassword());
		}
		return "redirect:/manage";
	}

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

	@RequestMapping(value = "/changeprice", method = RequestMethod.POST)
	public String changePrice(int prodId, int price) throws IOException {
		User u = getUser(request);
		ManagerDaoImpl mdi = new ManagerDaoImpl();
		mdi.updatePrice(price, u.getIdUser(), prodId);
		return "redirect:/manage";
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}
