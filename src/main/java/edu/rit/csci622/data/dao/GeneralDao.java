package edu.rit.csci622.data.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import edu.rit.csci622.model.Product;
import edu.rit.csci622.model.User;

public interface GeneralDao {

	/**
	 * Creates a user
	 * 
	 * @param user
	 * @param key
	 * @return
	 */
	public int createUser(@Param("user") User user, @Param("key") String key);

	/**
	 * Gets a user
	 * 
	 * @param userId
	 * @param key
	 * @return
	 */
	public User getUser(@Param("userId") int userId, @Param("key") String key);

	/**
	 * Creates a session
	 * 
	 * @param uuid
	 * @param userId
	 * @param key
	 */
	public void createSession(@Param("uuid") String uuid, @Param("userId") int userId, @Param("key") String key);

	/**
	 * Get a particular product. It returns a list of the changes to the price
	 * of a product, along with the product information.
	 * 
	 * @param productId
	 * @param key
	 * @return
	 */
	public List<Product> getProduct(@Param("productId") int productId, @Param("key") String key);

	/**
	 * Places an order
	 * 
	 * @param productId
	 * @param quantity
	 * @param userId
	 * @param key
	 * @return
	 */
	public int placeOrder(@Param("productId") int productId, @Param("qty") int quantity, @Param("userId") int userId,
			@Param("key") String key);

	/**
	 * Gets all products.
	 * 
	 * @param key
	 * @return
	 */
	public List<Product> getProducts(@Param("key") String key);

	/**
	 * Requests a hire
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public int requestHire(@Param("userId") int userId, @Param("roleId") int roleId);

	/**
	 * Logs the user off
	 * 
	 * @param uuid
	 * @param key
	 */
	public void deleteSession(@Param("uuid") String uuid, @Param("key") String key);

	/**
	 * Gets the users hashed password.
	 * 
	 * @param username
	 * @param key
	 * @return
	 */
	public Map<String, Object> getUserPassword(@Param("username") String username, @Param("key") String key);

	/**
	 * Gets a users details from their session
	 * 
	 * @param uuid
	 * @param key
	 * @return
	 */
	public Map<String, Object> getUserFromSession(@Param("uuid") String uuid, @Param("key") String key);

	/**
	 * Updates the user
	 * 
	 * @param uid
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param key
	 */
	public void updateUser(@Param("uid") int uid, @Param("first_name") String firstName,
			@Param("last_name") String lastName, @Param("email") String email, @Param("key") String key);

	/**
	 * Changes the users password.
	 * 
	 * @param idUser
	 * @param password
	 * @param key
	 */
	public void changePassword(@Param("uID") int idUser, @Param("password") String password, @Param("key") String key);

}
