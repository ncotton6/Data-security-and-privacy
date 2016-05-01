package edu.rit.csci622.data.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.jasypt.util.text.StrongTextEncryptor;

import edu.rit.csci622.data.PasswordHandler;
import edu.rit.csci622.data.dao.Dao;
import edu.rit.csci622.data.dao.GeneralDao;
import edu.rit.csci622.model.Product;
import edu.rit.csci622.model.User;

public class GeneralDaoImpl extends Dao implements GeneralDao {

	private final String resource = "edu/rit/csci622/data/dao/GeneralConfig.xml";
	private SqlSessionFactory factory;

	public GeneralDaoImpl() throws IOException {
		InputStream is = Resources.getResourceAsStream(resource);
		this.factory = new SqlSessionFactoryBuilder().build(is);
		is.close();
		this.encryptor.setPassword(PasswordHandler.getAppPassword());
	}

	public int createUser(User user, String key) {
		SqlSession session = factory.openSession();
		try {
			user.setFirst_name(encrypt(user.getFirst_name()));
			user.setLast_name(encrypt(user.getLast_name()));
			user.setEmail(encrypt(user.getEmail()));
			user.setPassword(passwordEncryptor.encryptPassword(user.getPassword()));
			int id = session.getMapper(GeneralDao.class).createUser(user, key);
			session.commit();
			return id;
		} finally {
			if (session != null)
				session.close();
		}
	}

	public User getUser(int userId, String key) {
		SqlSession session = factory.openSession();
		try {
			User user = session.getMapper(GeneralDao.class).getUser(userId, key);
			user.setFirst_name(decrypt(user.getFirst_name()));
			user.setLast_name(decrypt(user.getLast_name()));
			user.setEmail(decrypt(user.getEmail()));
			session.commit();
			return user;
		} finally {
			if (session != null)
				session.close();
		}
	}

	public void createSession(String uuid, int userId, String key) {
		SqlSession session = factory.openSession();
		try {
			session.getMapper(GeneralDao.class).createSession(uuid, userId, key);
			session.commit();
		} finally {
			if (session != null)
				session.close();
		}
	}

	public List<Product> getProduct(int productId, String key) {
		SqlSession session = factory.openSession();
		try {
			List<Product> products = session.getMapper(GeneralDao.class).getProduct(productId, key);
			List<Product> ret = new ArrayList<Product>(products.size());
			for (Product p : products) {
				p.setName(decrypt(p.getName()));
				p.setDescription(decrypt(p.getDescription()));
				ret.add(p);
			}
			session.commit();
			return ret;
		} finally {
			if (session != null)
				session.close();
		}
	}

	public int placeOrder(int productId, int quantity, int userId, String key) {
		SqlSession session = factory.openSession();
		try {
			int orderId = session.getMapper(GeneralDao.class).placeOrder(productId, quantity, userId, key);
			session.commit();
			return orderId;
		} finally {
			if (session != null)
				session.close();
		}
	}

	public List<Product> getProducts(String key) {
		SqlSession session = factory.openSession();
		try {
			List<Product> products = session.getMapper(GeneralDao.class).getProducts(key);
			List<Product> ret = new ArrayList<Product>(products.size());
			for (Product p : products) {
				p.setName(decrypt(p.getName()));
				p.setDescription(decrypt(p.getDescription()));
				System.out.println(p.getDate());
			}
			return products;
		} finally {
			if (session != null)
				session.close();
		}
	}

	public int requestHire(int userId, int roleId) {
		SqlSession session = factory.openSession();
		try {
			int requestId = session.getMapper(GeneralDao.class).requestHire(userId, roleId);
			session.commit();
			return requestId;
		} finally {
			if (session != null)
				session.close();
		}
	}

	public void deleteSession(int sessionId) {
		SqlSession session = factory.openSession();
		try {
			session.getMapper(GeneralDao.class).deleteSession(sessionId);
			session.commit();
		} finally {
			if (session != null)
				session.close();
		}
	}

	public Map<String, Object> getUserPassword(String username, String key) {
		SqlSession session = factory.openSession();
		try {
			Map<String, Object> map = session.getMapper(GeneralDao.class).getUserPassword(username, key);
			map.put("username", new String((byte[]) map.get("username")));
			map.put("password", new String((byte[]) map.get("password")));
			session.commit();
			return map;
		} finally {
			if (session != null)
				session.close();
		}
	}

	public Map<String, Object> getUserFromSession(String uuid, String key) {
		SqlSession session = factory.openSession();
		try {
			Map<String, Object> map = session.getMapper(GeneralDao.class).getUserFromSession(uuid, key);
			session.commit();
			return map;
		} finally {
			if (session != null)
				session.close();
		}
	}

	public void updateUser(int uid, String firstName, String lastName, String email, String key) {
		SqlSession session = factory.openSession();
		try {
			session.getMapper(GeneralDao.class).updateUser(uid, encrypt(firstName), encrypt(lastName), encrypt(email), key);
			session.commit();
		} finally {
			if (session != null)
				session.close();
		}
	}

	public void changePassword(int idUser, String password, String key) {
		SqlSession session = factory.openSession();
		try {
			session.getMapper(GeneralDao.class).changePassword(idUser, passwordEncryptor.encryptPassword(password), key);
			session.commit();
		} finally {
			if (session != null)
				session.close();
		}
	}

}
