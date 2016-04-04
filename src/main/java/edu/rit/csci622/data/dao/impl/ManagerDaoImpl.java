package edu.rit.csci622.data.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import edu.rit.csci622.data.PasswordHandler;
import edu.rit.csci622.data.dao.EmployeeHandlerDao;
import edu.rit.csci622.data.dao.ProductHandlerDao;
import edu.rit.csci622.model.Hire;

public class ManagerDaoImpl extends EmployeerDaoImpl implements ProductHandlerDao, EmployeeHandlerDao {

	private final String resource = "edu/rit/csci622/data/dao/ManagerConfig.xml";
	private SqlSessionFactory factory;

	public ManagerDaoImpl() throws IOException {
		super();
		InputStream is = Resources.getResourceAsStream(resource);
		this.factory = new SqlSessionFactoryBuilder().build(is);
		is.close();
	}

	public void fireEmployee(int userId) {
		SqlSession session = factory.openSession();
		try {
			session.getMapper(EmployeeHandlerDao.class).fireEmployee(userId);
			session.commit();
		} finally {
			if (session != null)
				session.close();
		}
	}

	public List<Hire> getHire() {
		SqlSession session = factory.openSession();
		try {
			List<Hire> ret = session.getMapper(EmployeeHandlerDao.class).getHire();
			session.commit();
			return ret;
		} finally {
			if (session != null)
				session.close();
		}
	}

	public void managerHireSignOff(int userId, int managerId, int roleId) {
		SqlSession session = factory.openSession();
		try {
			session.getMapper(EmployeeHandlerDao.class).managerHireSignOff(userId, managerId, roleId);
			session.commit();
		} finally {
			if (session != null)
				session.close();
		}
	}

	public void updatePrice(int price, int changedBy, int productId) {
		SqlSession session = factory.openSession();
		try {
			session.getMapper(ProductHandlerDao.class).updatePrice(price, changedBy, productId);
			session.commit();
		} finally {
			if (session != null)
				session.close();
		}
	}

	public int createProduct(String name, String description, boolean active, int price, int user, String key) {
		SqlSession session = factory.openSession();
		try {
			int ret = session.getMapper(ProductHandlerDao.class).createProduct(encrypt(name), encrypt(description),
					active, price, user, PasswordHandler.getDbPassword());
			session.commit();
			return ret;
		} finally {
			if (session != null)
				session.close();
		}
	}

	public void updateProduct(int productId, String name, String description, boolean active, String key) {
		SqlSession session = factory.openSession();
		try{
			session.getMapper(ProductHandlerDao.class).updateProduct(productId, encrypt(name), encrypt(description), active, PasswordHandler.getDbPassword());
			session.commit();
		}finally{
			if(session != null)
				session.close();
		}
	}

}
