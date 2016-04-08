package edu.rit.csci622.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.rit.csci622.auth.Auth;

@Controller
@RequestMapping("/login")
@Auth
public class LoginInController {

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model modal) {
		return "login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String login(String username, String password, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Cookie c = new Cookie("ecommsession", "test");
		c.setMaxAge(3600);
		response.addCookie(c);
		String uri = request.getContextPath();
		response.sendRedirect(uri+"/");
		return "index";
	}

}
