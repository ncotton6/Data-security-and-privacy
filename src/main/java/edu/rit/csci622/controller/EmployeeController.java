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
import edu.rit.csci622.data.dao.OrderHandlerDao;
import edu.rit.csci622.data.dao.impl.EmployeeDaoImpl;
import edu.rit.csci622.model.Order;

@Controller
@Auth(roles = { Role.EMPLOYEE, Role.MANAGER })
@RequestMapping("/emp")
public class EmployeeController extends edu.rit.csci622.controller.Controller {

	@Autowired
	private HttpServletRequest request;

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) throws IOException {
		OrderHandlerDao dao = new EmployeeDaoImpl();
		List<Order> orders = dao.getOrders();
		model.addAttribute("orders", orders);
		return "emp";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String fulFill(int orderId) throws IOException {
		OrderHandlerDao dao = new EmployeeDaoImpl();
		dao.fulfillOrder(orderId, getUser(request).getIdUser());
		return "redirect:/emp";
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
}
