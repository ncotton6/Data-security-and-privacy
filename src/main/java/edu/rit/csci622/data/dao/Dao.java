package edu.rit.csci622.data.dao;

import org.apache.ibatis.annotations.Param;

import edu.rit.csci622.model.User;

public interface Dao {

	public int createUser(@Param("user") User user, @Param("key") String key);

	public User getUser(@Param("userId") int userId, @Param("key") String key);

}
