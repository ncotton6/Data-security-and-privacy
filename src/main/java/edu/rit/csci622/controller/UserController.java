package edu.rit.csci622.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
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
public class UserController {

	@Auth(roles = { Role.CUSTOMER, Role.EMPLOYEE, Role.HR, Role.MANAGER })
	@RequestMapping(method = RequestMethod.GET)
	public String index(int userId) {
		return "user";
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
		return "redirect:/user?userId=" + id;
	}

}
