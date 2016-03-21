package edu.rit.csci622.data.dao.impl;

import java.util.List;

import edu.rit.csci622.data.dao.EmployeeHandlerDao;
import edu.rit.csci622.data.dao.ProductHandlerDao;
import edu.rit.csci622.model.Hire;

public class ManagerDaoImpl implements ProductHandlerDao, EmployeeHandlerDao{

	public void fireEmployee(int userId) {
		// TODO Auto-generated method stub
		
	}

	public List<Hire> getHire() {
		// TODO Auto-generated method stub
		return null;
	}

	public void managerHireSignOff(int userId, int managerId, int roleId) {
		// TODO Auto-generated method stub
		
	}

	public void updatePrice(int price, int changedBy, int productId) {
		// TODO Auto-generated method stub
		
	}

	public int createProduct(String name, String description, boolean active, int price, int user, String key) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void updateProduct(int productId, String name, String description, boolean active, String key) {
		// TODO Auto-generated method stub
		
	}

}
