package edu.rit.csci622.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/signin/")
public class SignInController {

	@RequestMapping(method = RequestMethod.GET)
	public String index(){
		return "signin";
	}
	
}
