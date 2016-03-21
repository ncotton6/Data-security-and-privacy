package edu.rit.csci622.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.rit.csci622.model.Hire;

public interface HREmployeeHandlerDao {

	public void hrHireSignOff(@Param("userId") int userId, @Param("hrId") int hrId, @Param("roleId") int roleId);
	
	public List<Hire> getHire();

}
