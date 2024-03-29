package edu.rit.csci622.controller;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.rit.csci622.auth.Auth;
import edu.rit.csci622.data.PasswordHandler;
import edu.rit.csci622.data.dao.GeneralDao;
import edu.rit.csci622.data.dao.impl.GeneralDaoImpl;

/**
 * In order to interact with the application a user must first go through this
 * login module and receive the special session id. Which will be stored in a
 * cookie.
 * 
 * @author Nathaniel Cotton
 *
 */
@Controller
@RequestMapping("/login")
@Auth
public class LoginInController {

	private BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

	/**
	 * Brings up the login page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		return "login";
	}

	/**
	 * Allows a user to login
	 * 
	 * @param username
	 * @param password
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String login(String username, String password, HttpServletResponse response) throws IOException {
		GeneralDao dao = new GeneralDaoImpl();
		if (username != null && password != null && !username.trim().isEmpty() && !password.trim().isEmpty()) {
			Map<String, Object> user = dao.getUserPassword(username, PasswordHandler.getDbPassword());
			if (user != null) {
				String dbPassword = (String) user.get("password");
				int userId = (Integer) user.get("idUser");
				if (passwordEncryptor.checkPassword(password, dbPassword)) {
					UUID uuid = UUID.randomUUID();
					dao.createSession(uuid.toString(), userId, PasswordHandler.getDbPassword());
					Cookie c = new Cookie("ecommsession", uuid.toString());
					c.setMaxAge(3600); // login lasts for one hour
					response.addCookie(c);
					return "redirect:/";
				}
			}
		}
		return "redirect:/login";
	}

}
