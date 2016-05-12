package edu.rit.csci622.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.rit.csci622.auth.Auth;
import edu.rit.csci622.auth.Role;
import edu.rit.csci622.data.PasswordHandler;
import edu.rit.csci622.data.Util;
import edu.rit.csci622.data.dao.impl.HrDaoImpl;
import edu.rit.csci622.model.Hire;
import edu.rit.csci622.model.User;

/**
 * This class implements all the functionality needed by an HR representative.
 * 
 * @author Nathaniel Cotton
 *
 */
@org.springframework.stereotype.Controller
@Auth(roles = { Role.HR })
@RequestMapping("/hr")
public class HRController extends Controller {

	@Autowired
	private HttpServletRequest request;

	/**
	 * The index page will bring up all of the users who have requested to be
	 * hired, and have not already been signed off on by an HR representative.
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) throws IOException {
		HrDaoImpl dao = new HrDaoImpl();
		List<Hire> hireRequests = Util.filterHRSignoff(dao.getHire());
		HashMap<Integer, List<User>> requests = new HashMap<Integer, List<User>>();
		requests.put(Role.MANAGER.roleId, new ArrayList<User>());
		requests.put(Role.HR.roleId, new ArrayList<User>());
		requests.put(Role.EMPLOYEE.roleId, new ArrayList<User>());
		for (Hire h : hireRequests) {
			if (requests.containsKey(h.getRequestedRole()))
				requests.get(h.getRequestedRole()).add(dao.getUser(h.getIdUser(), PasswordHandler.getDbPassword()));
		}
		model.addAttribute("managerRequest", requests.get(Role.MANAGER.roleId));
		model.addAttribute("hrRequest", requests.get(Role.HR.roleId));
		model.addAttribute("employeeRequest", requests.get(Role.EMPLOYEE.roleId));
		return "hr";
	}

	/**
	 * Signs off on a request for hire
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String hire(int userId, int roleId) throws IOException {
		HrDaoImpl dao = new HrDaoImpl();
		User u = getUser(request);
		dao.hrHireSignOff(userId, u.getIdUser(), roleId);
		return "redirect:/hr";
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}
