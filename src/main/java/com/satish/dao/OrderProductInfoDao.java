package com.satish.dao;

import java.util.List;

import com.satish.model.OrderProductInfo;


public interface OrderProductInfoDao {
	public int addOrderProductInfoId(List<OrderProductInfo> orderProductInfo,int orderId);
	public List<OrderProductInfo> viewProductByOrderId(int orderId);
}
