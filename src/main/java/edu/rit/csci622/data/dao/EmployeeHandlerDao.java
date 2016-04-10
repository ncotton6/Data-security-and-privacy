package edu.rit.csci622.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.rit.csci622.model.Hire;
import edu.rit.csci622.model.User;

public interface EmployeeHandlerDao {

	public void fireEmployee(@Param("userId") int userId);

	public List<Hire> getHire();

	public void managerHireSignOff(@Param("userId") int userId, @Param("managerId") int managerId,
			@Param("roleId") int roleId);
	
	public List<User> getUsers(@Param("key") String key);

}
