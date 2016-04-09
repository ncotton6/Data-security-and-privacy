package edu.rit.csci622.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import edu.rit.csci622.auth.Auth;
import edu.rit.csci622.auth.Role;
import edu.rit.csci622.controller.LoginInController;
import edu.rit.csci622.controller.UserController;
import edu.rit.csci622.data.PasswordHandler;
import edu.rit.csci622.data.dao.GeneralDao;
import edu.rit.csci622.data.dao.impl.GeneralDaoImpl;
import edu.rit.csci622.model.User;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = null;
	private static GeneralDao dao;

	public LoginInterceptor() throws IOException {
		this.dao = new GeneralDaoImpl();
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws IOException {
		Cookie[] cookies = request.getCookies();
		Role[] roles = new Role[] { Role.ANONYMOUS };
		HandlerMethod hm = null;
		if (handler instanceof HandlerMethod)
			hm = (HandlerMethod) handler;
		Method m = hm.getMethod();
		Auth auth = m.getAnnotation(Auth.class);
		auth = auth == null ? m.getDeclaringClass().getAnnotation(Auth.class) : auth;
		if (auth != null) {
			roles = auth.roles();
			for (Role r : roles) {
				if (r == Role.ANONYMOUS)
					return true;
			}
			if (cookies != null)
				for (Cookie c : cookies) {
					if ("ecommsession".equals(c.getName())) {
						GeneralDao dao = new GeneralDaoImpl();
						Map<String, Object> map = dao.getUserFromSession(c.getValue(), PasswordHandler.getDbPassword());
						if (map != null) {
							int userId = (Integer) map.get("userId");
							User u = dao.getUser(userId, PasswordHandler.getDbPassword());
							int roleId = u.getRole();
							for (Role r : roles) {
								if (r.roleId == roleId)
									return true;
							}
						}
					}
				}
		}
		String uri = request.getContextPath();
		response.sendRedirect(uri + "/login");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
