package edu.rit.csci622.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.rit.csci622.auth.Auth;
import edu.rit.csci622.auth.Role;
import edu.rit.csci622.data.PasswordHandler;
import edu.rit.csci622.data.dao.GeneralDao;
import edu.rit.csci622.data.dao.impl.GeneralDaoImpl;
import edu.rit.csci622.model.User;

@RequestMapping("/user")
@Controller
@Auth
public class UserController extends edu.rit.csci622.controller.Controller {

	@Autowired
	private HttpServletRequest request;

	@Auth(roles = { Role.CUSTOMER, Role.EMPLOYEE, Role.HR, Role.MANAGER })
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		try {
			User user = getUser(request);
			model.addAttribute("user", user);
			return "user";
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return "redirect:/login";
	}

	@Auth(roles = { Role.CUSTOMER, Role.EMPLOYEE, Role.HR, Role.MANAGER })
	@RequestMapping(method = RequestMethod.POST)
	public String updateUser(User u) {
		try {
			User user = getUser(request);
			GeneralDao dao = new GeneralDaoImpl();
			dao.updateUser(user.getIdUser(), u.getFirst_name(), u.getLast_name(), u.getEmail(),
					PasswordHandler.getDbPassword());
			return "redirect:/user";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/login";
	}

	@Auth(roles = { Role.CUSTOMER, Role.EMPLOYEE, Role.HR, Role.MANAGER })
	@RequestMapping(value = "/role", method = RequestMethod.POST)
	public String requestRole(int roleId) throws IOException {
		User u = getUser(request);
		GeneralDao dao = new GeneralDaoImpl();
		dao.requestHire(u.getIdUser(), roleId);
		return "redirect:/user";
	}
	
	@Auth(roles = { Role.CUSTOMER, Role.EMPLOYEE, Role.HR, Role.MANAGER })
	@RequestMapping(value = "/password", method = RequestMethod.POST)
	public String changePassword(String password) throws IOException{
		User u = getUser(request);
		GeneralDao dao = new GeneralDaoImpl();
		dao.changePassword(u.getIdUser(),password,PasswordHandler.getDbPassword());
		return "redirect:/user";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createUser(String username, String firstName, String lastName, String email, String password)
			throws IOException {
		GeneralDao dao = new GeneralDaoImpl();
		User user = new User();
		user.setEmail(email);
		user.setUsername(username);
		user.setFirst_name(firstName);
		user.setLast_name(lastName);
		user.setPassword(password);
		int id = dao.createUser(user, PasswordHandler.getDbPassword());
		return "redirect:/user";
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}
