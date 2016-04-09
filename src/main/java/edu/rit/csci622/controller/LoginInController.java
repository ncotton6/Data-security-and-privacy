package edu.rit.csci622.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.rit.csci622.auth.Auth;
import edu.rit.csci622.data.PasswordHandler;
import edu.rit.csci622.data.dao.GeneralDao;
import edu.rit.csci622.data.dao.impl.GeneralDaoImpl;

@Controller
@RequestMapping("/login")
@Auth
public class LoginInController {

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model modal) {
		return "login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String login(String username, String password, HttpServletResponse response) throws IOException {
		System.out.println("Username: " + username);
		System.out.println("Password: " + password);
		GeneralDao dao = new GeneralDaoImpl();
		Map<String, String> user = dao.getUserPassword(username, PasswordHandler.getDbPassword());
		System.out.println("User: " + user);
		if (user != null) {
			System.out.println("Keys: " + Arrays.toString(user.keySet().toArray()));
			String dbPassword = user.get("password");
			int userId = Integer.parseInt(user.get("idUser"));
			if (dbPassword.equals(password)) {
				UUID uuid = UUID.randomUUID();
				dao.createSession(uuid.toString(), userId, PasswordHandler.getDbPassword());
				Cookie c = new Cookie("ecommsession", uuid.toString());
				c.setMaxAge(3600); // login lasts for one hour
				response.addCookie(c);
				return "redirect:/";
			}
		}
		return "redirect:/login";
	}

}
