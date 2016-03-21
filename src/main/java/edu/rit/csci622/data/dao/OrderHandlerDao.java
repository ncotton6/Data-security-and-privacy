package edu.rit.csci622.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.rit.csci622.model.Order;

public interface OrderHandlerDao {

	public void fulfillOrder(@Param("orderId")int orderId, @Param("fullfillerId")int fulfillerId);
	
	public List<Order> getOrders();
	
}
