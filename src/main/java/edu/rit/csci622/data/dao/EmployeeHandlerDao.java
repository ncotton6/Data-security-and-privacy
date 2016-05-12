package edu.rit.csci622.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.rit.csci622.model.Hire;
import edu.rit.csci622.model.User;

public interface EmployeeHandlerDao {

	/**
	 * Fires and employee
	 * 
	 * @param userId
	 */
	public void fireEmployee(@Param("userId") int userId);

	/**
	 * Gets all hire requests
	 * 
	 * @return
	 */
	public List<Hire> getHire();

	/**
	 * Signs off on a hire
	 * 
	 * @param userId
	 * @param managerId
	 * @param roleId
	 */
	public void managerHireSignOff(@Param("userId") int userId, @Param("managerId") int managerId,
			@Param("roleId") int roleId);
	
	/**
	 * Gets all users
	 * 
	 * @param key
	 * @return
	 */
	public List<User> getUsers(@Param("key") String key);

}
