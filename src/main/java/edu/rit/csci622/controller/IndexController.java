package edu.rit.csci622.controller;

import java.io.IOException;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.rit.csci622.data.dao.GeneralDao;
import edu.rit.csci622.data.dao.impl.GeneralDaoImpl;
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
		GeneralDao d = new GeneralDaoImpl();
		User u = new User();
		u.setEmail("ncotton6gmail.com");
		u.setFirst_name("Nate");
		u.setLast_name("Cotton");
		u.setPassword("test");
		u.setUsername("ncotton6");
		System.out.println(u);
		int id = d.createUser(u,"test");
		System.out.println("User ID is :: " + id);
		User u2 = d.getUser(id, "test");
		System.out.println(u2);
		
		return "index";
	}

}
