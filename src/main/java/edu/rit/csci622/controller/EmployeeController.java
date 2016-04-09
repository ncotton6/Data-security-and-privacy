package edu.rit.csci622.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.rit.csci622.auth.Auth;
import edu.rit.csci622.auth.Role;

@Controller
@Auth(roles={Role.EMPLOYEE,Role.MANAGER})
@RequestMapping("/emp")
public class EmployeeController extends edu.rit.csci622.controller.Controller {

	@Autowired
	private HttpServletRequest request;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
}
