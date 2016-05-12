package edu.rit.csci622.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.rit.csci622.model.Hire;

public interface HREmployeeHandlerDao {

	/**
	 * HR signoff on the user hire
	 * 
	 * @param userId
	 * @param hrId
	 * @param roleId
	 */
	public void hrHireSignOff(@Param("userId") int userId, @Param("hrId") int hrId, @Param("roleId") int roleId);
	
	/**
	 * Gets all the hire requests
	 * 
	 * @return
	 */
	public List<Hire> getHire();

}
