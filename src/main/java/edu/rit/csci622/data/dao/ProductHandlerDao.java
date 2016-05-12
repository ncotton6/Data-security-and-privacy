package edu.rit.csci622.data.dao;

import org.apache.ibatis.annotations.Param;

public interface ProductHandlerDao {

	/**
	 * Updates the price of a product
	 * 
	 * @param price
	 * @param changedBy
	 * @param productId
	 */
	public void updatePrice(@Param("price")int price, @Param("changedBy")int changedBy, @Param("productId")int productId);

	/**
	 * Creates a product 
	 * 
	 * @param name
	 * @param description
	 * @param active
	 * @param price
	 * @param user
	 * @param key
	 * @return
	 */
	public int createProduct(@Param("name") String name, @Param("description") String description,
			@Param("active") boolean active, @Param("price") int price, @Param("user") int user,
			@Param("key") String key);

	/**
	 * Updates a product
	 * 
	 * @param productId
	 * @param name
	 * @param description
	 * @param active
	 * @param key
	 */
	public void updateProduct(@Param("productId")int productId, @Param("name")String name, @Param("description")String description, @Param("active")boolean active, @Param("key")String key);

}
