package edu.rit.csci622.data.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import edu.rit.csci622.data.dao.CustomerDao;
import edu.rit.csci622.model.Product;
import edu.rit.csci622.model.User;

public class CustomerDaoImpl implements CustomerDao {

	private String resource = "edu/rit/csci622/data/dao/mybatis_config.xml";
	private SqlSessionFactory factory;
	
	public CustomerDaoImpl() throws IOException{
		InputStream is = Resources.getResourceAsStream(resource);
		this.factory = new SqlSessionFactoryBuilder().build(is);
		is.close();
	}

	public int createUser(User user, String key) {
		SqlSession session = factory.openSession();
		try{
			int id = session.getMapper(CustomerDao.class).createUser(user, key);
			session.commit();
			return id;
		}finally{
			if(session != null)
				session.close();
		}
	}

	public User getUser(int userId, String key) {
		SqlSession session = factory.openSession();
		try{
			User user = session.getMapper(CustomerDao.class).getUser(userId, key);
			session.commit();
			return user;
		}finally{
			if(session != null)
				session.close();
		}
	}

	public void createSession(String uuid, int userId, String key) {
		SqlSession session = factory.openSession();
		try{
			session.getMapper(CustomerDao.class).createSession(uuid, userId, key);
			session.commit();
		}finally{
			if(session != null)
				session.close();
		}
	}

	public List<Product> getProduct(int productId, String key) {
		SqlSession session = factory.openSession();
		try{
			List<Product> products = session.getMapper(CustomerDao.class).getProduct(productId, key);
			session.commit();
			return products;
		}finally{
			if(session != null)
				session.close();
		}
	}

	public int placeOrder(int productId, int quantity, int userId, String key) {
		SqlSession session = factory.openSession();
		try{
			int orderId = session.getMapper(CustomerDao.class).placeOrder(productId, quantity, userId, key);
			session.commit();
			return orderId;
		}finally{
			if(session != null)
				session.close();
		}
	}

	public List<Product> getProducts(String key) {
		SqlSession session = factory.openSession();
		try{
			List<Product> products = session.getMapper(CustomerDao.class).getProducts(key);
			session.commit();
			return products;
		}finally{
			if(session != null)
				session.close();
		}
	}

	public int requestHire(int userId, int roleId) {
		SqlSession session = factory.openSession();
		try{
			int requestId = session.getMapper(CustomerDao.class).requestHire(userId, roleId);
			session.commit();
			return requestId;
		}finally{
			if(session != null)
				session.close();
		}
	}

	public void deleteSession(int sessionId) {
		SqlSession session = factory.openSession();
		try{
			session.getMapper(CustomerDao.class).deleteSession(sessionId);;
			session.commit();
		}finally{
			if(session != null)
				session.close();
		}
	}

	public Map<String, String> getUserPassword(String username, String key) {
		SqlSession session = factory.openSession();
		try{
			Map<String,String> map = session.getMapper(CustomerDao.class).getUserPassword(username, key);
			session.commit();
			return map;
		}finally{
			if(session != null)
				session.close();
		}
	}
	
}
