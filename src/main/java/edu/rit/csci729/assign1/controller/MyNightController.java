package edu.rit.csci729.assign1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Entry point into the application.
 * 
 * @author Nathaniel Cotton
 *
 */
@Controller
@RequestMapping("/")
public class MyNightController {

	/**
	 * Entry point into the application.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap model) {
		return "index";
	}

}
