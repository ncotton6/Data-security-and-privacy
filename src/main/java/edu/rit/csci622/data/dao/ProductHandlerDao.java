package edu.rit.csci622.data.dao;

import org.apache.ibatis.annotations.Param;

public interface ProductHandlerDao {

	public void updatePrice(@Param("price")int price, @Param("changedBy")int changedBy, @Param("productId")int productId);

	public int createProduct(@Param("name") String name, @Param("description") String description,
			@Param("active") boolean active, @Param("price") int price, @Param("user") int user,
			@Param("key") String key);

	public void updateProduct(@Param("productId")int productId, @Param("name")String name, @Param("description")String description, @Param("active")boolean active, @Param("key")String key);

}
