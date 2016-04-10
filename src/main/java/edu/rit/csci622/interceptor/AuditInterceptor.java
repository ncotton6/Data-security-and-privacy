package edu.rit.csci622.interceptor;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jasypt.util.text.BasicTextEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import edu.rit.csci622.data.PasswordHandler;
import edu.rit.csci622.data.dao.GeneralDao;
import edu.rit.csci622.data.dao.impl.GeneralDaoImpl;
import edu.rit.csci622.model.User;

public class AuditInterceptor extends HandlerInterceptorAdapter {

	final static Logger logger = LoggerFactory.getLogger(AuditInterceptor.class);
	static BasicTextEncryptor encryptor = new BasicTextEncryptor();

	static {
		encryptor.setPassword(PasswordHandler.getAppPassword());
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		try {
			logger.info("User {} is accessing {}", getUser(request).getIdUser(), request.getRequestURL().toString());
		} catch (Exception e) {
			logger.info("Unidentified User is accessing {}", request.getRequestURL());
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		System.out.println(logger.isInfoEnabled() + " INFO ENABLED");
		try {
			logger.info("User {} executed {}", getUser(request).getIdUser(), request.getRequestURL().toString());
		} catch (Exception e) {
			logger.info("Unidentified User executed {}", request.getRequestURL());
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	private User getUser(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("ecommsession")) {
					String value = c.getValue();
					try {
						GeneralDao dao = new GeneralDaoImpl();
						Map<String, Object> user = dao.getUserFromSession(value, PasswordHandler.getDbPassword());
						int id = (Integer) user.get("userId");
						User u = dao.getUser(id, PasswordHandler.getDbPassword());
						return u;
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		}
		throw new RuntimeException("Couldn't find user");
	}
}
