package edu.rit.csci622.data.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import edu.rit.csci622.model.Product;
import edu.rit.csci622.model.User;

public interface GeneralDao {

	public int createUser(@Param("user") User user, @Param("key") String key);

	public User getUser(@Param("userId") int userId, @Param("key") String key);

	public void createSession(@Param("uuid") String uuid, @Param("userId") int userId, @Param("key") String key);

	public List<Product> getProduct(@Param("productId") int productId, @Param("key") String key);

	public int placeOrder(@Param("productId") int productId, @Param("qty") int quantity, @Param("userId") int userId,
			@Param("key") String key);

	public List<Product> getProducts(@Param("key") String key);

	public int requestHire(@Param("userId") int userId, @Param("roleId") int roleId);

	public void deleteSession(@Param("sessionId") int sessionId);

	public Map<String, String> getUserPassword(@Param("username") String username, @Param("key") String key);

}
