package edu.rit.csci622.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;

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
import edu.rit.csci622.data.dao.GeneralDao;
import edu.rit.csci622.data.dao.impl.GeneralDaoImpl;

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
		if(m.getDeclaringClass() == LoginInController.class)
			return true;
		Auth auth = m.getDeclaringClass().getAnnotation(Auth.class);
		if (auth != null) {
			roles = auth.roles();
		}
		if (cookies != null)
			for (Cookie c : cookies) {
				if ("ecommsession".equals(c.getName())) {
					// add the cookie check
					// if fail redirect
					// get annotation off of the handler
					return true;
				}
			}
		String uri = request.getContextPath();
		response.sendRedirect(uri+"/login");
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
