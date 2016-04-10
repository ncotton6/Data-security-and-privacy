package edu.rit.csci622.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.rit.csci622.auth.Auth;
import edu.rit.csci622.auth.Role;
import edu.rit.csci622.data.PasswordHandler;
import edu.rit.csci622.data.Util;
import edu.rit.csci622.data.dao.GeneralDao;
import edu.rit.csci622.data.dao.impl.GeneralDaoImpl;
import edu.rit.csci622.model.Product;
import edu.rit.csci622.model.User;

@RequestMapping("/dash")
@Controller
@Auth(roles = { Role.CUSTOMER, Role.EMPLOYEE, Role.HR, Role.MANAGER })
public class CustomerController extends edu.rit.csci622.controller.Controller {

	private GeneralDao dao;
	@Autowired
	private HttpServletRequest request;

	public CustomerController() throws IOException {
		dao = new GeneralDaoImpl();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		List<Product> products = Util.filterActive(Util.filterProductsForNow(dao.getProducts(PasswordHandler.getDbPassword())));
		model.addAttribute("products", products);
		return "purchase";
	}

	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String order(int qty, int product) {
		User u = getUser(request);
		dao.placeOrder(product, qty, u.getIdUser(), PasswordHandler.getDbPassword());
		return "redirect:/dash";
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}
