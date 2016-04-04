package edu.rit.csci622.data.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import edu.rit.csci622.data.dao.OrderHandlerDao;
import edu.rit.csci622.model.Order;

public class EmployeerDaoImpl extends GeneralDaoImpl implements OrderHandlerDao {

	private final String resource = "edu/rit/csci622/data/dao/EmployeeConfig.xml";
	private SqlSessionFactory factory;
	
	public EmployeerDaoImpl() throws IOException {
		super();
		InputStream is = Resources.getResourceAsStream(resource);
		this.factory = new SqlSessionFactoryBuilder().build(is);
		is.close();
	}

	public void fulfillOrder(int orderId, int fulfillerId) {
		// TODO Auto-generated method stub
		
	}

	public List<Order> getOrders() {
		// TODO Auto-generated method stub
		return null;
	}

}
