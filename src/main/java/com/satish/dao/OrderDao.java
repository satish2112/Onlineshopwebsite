package com.satish.dao;

import java.util.List;

import com.satish.model.Order;

public interface OrderDao {
	int placeOrder(Order order);
	int cancelOrder(int orderId);
	Order viewOrder(int orderId);
	List<Order> viewallOrders(int customerId);
}
