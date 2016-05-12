package edu.rit.csci622.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import edu.rit.csci622.data.PasswordHandler;
import edu.rit.csci622.data.dao.GeneralDao;
import edu.rit.csci622.data.dao.impl.GeneralDaoImpl;
import edu.rit.csci622.model.User;

/**
 * Base controller that will supply functionality that would be desireable in
 * all controllers.
 * 
 * @author Nathaniel Cotton
 *
 */
public class Controller {

	/**
	 * Gets a user object from the cookie stored in the request
	 * 
	 * @param request
	 * @return
	 */
	public User getUser(HttpServletRequest request) {

		String value = getSessionUUID(request);
		try {
			GeneralDao dao = new GeneralDaoImpl();
			Map<String, Object> user = dao.getUserFromSession(value, PasswordHandler.getDbPassword());
			int id = (Integer) user.get("userId");
			User u = dao.getUser(id, PasswordHandler.getDbPassword());
			return u;
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("Couldn't find user");
	}

	/**
	 * Gets the cookie id from the request.
	 * 
	 * @param request
	 * @return
	 */
	public String getSessionUUID(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("ecommsession")) {
					return c.getValue();
				}
			}
		}
		return null;
	}

}
