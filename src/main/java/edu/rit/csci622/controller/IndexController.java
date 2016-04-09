package edu.rit.csci622.controller;

import java.io.IOException;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.rit.csci622.auth.Auth;
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
@Auth
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
		return "index";
	}

}
