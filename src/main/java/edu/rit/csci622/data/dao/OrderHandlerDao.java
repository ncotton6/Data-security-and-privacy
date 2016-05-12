package edu.rit.csci622.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.rit.csci622.model.Order;

public interface OrderHandlerDao {

	/**
	 * Fulfills an order
	 * 
	 * @param orderId
	 * @param fulfillerId
	 */
	public void fulfillOrder(@Param("orderId")int orderId, @Param("fullfillerId")int fulfillerId);
	
	/**
	 * Gets the orders placed by users
	 * 
	 * @return
	 */
	public List<Order> getOrders();
	
}
