package edu.rit.csci622.interceptor;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

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
		for (Cookie c : cookies) {
			if ("ecommsession".equals(c.getName())) {
				// add the cookie check
				// if fail redirect
				return true;
			}
		}
		response.sendRedirect("/login");
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
