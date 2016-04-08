package edu.rit.csci622.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.rit.csci622.auth.Auth;

@Controller
@RequestMapping("/signin")
@Auth
public class SignInController {

	
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(){
		return "signin";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String login(HttpServletResponse response){
		Cookie c = new Cookie("ecommsession", "test");
		c.setMaxAge(3600);
		response.addCookie(c);
		
		return "index";
	}
	
}
