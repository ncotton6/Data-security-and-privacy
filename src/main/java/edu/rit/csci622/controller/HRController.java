package edu.rit.csci622.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.rit.csci622.auth.Auth;
import edu.rit.csci622.auth.Role;

@org.springframework.stereotype.Controller
@Auth(roles = { Role.HR })
@RequestMapping("/hr")
public class HRController extends Controller {

	@Autowired
	private HttpServletRequest request;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}
