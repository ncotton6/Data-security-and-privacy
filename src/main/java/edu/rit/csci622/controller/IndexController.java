package edu.rit.csci622.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.rit.csci622.data.dao.Dao;
import edu.rit.csci622.data.dao.DaoImpl;
import edu.rit.csci622.model.User;

/**
 * Entry point into the application.
 * 
 * @author Nathaniel Cotton
 *
 */
@Controller
@RequestMapping("/")
public class IndexController {

	/**
	 * Entry point into the application.
	 * 
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap model) throws IOException {
		
		// TESTING
		Dao d = new DaoImpl();
		User u = new User();
		u.setEmail("ncotton6@gmail.com");
		u.setFirst_name("Nate");
		u.setLast_name("Cotton");
		u.setPassword("test");
		u.setUsername("ncotton6");
		d.createUser(u,"test");
		
		return "index";
	}

}
