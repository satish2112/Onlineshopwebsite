package com.satish.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.satish.dao.OrderDao;
import com.satish.dbconnection.DbConnection;
import com.satish.model.Order;

public class OrderDaoImpl implements OrderDao {
	Connection con = DbConnection.getDatabaseConnection();
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@Override
	public int placeOrder(Order order) {
		try {
			pstmt = con.prepareStatement("insert into order (orderId,customerId, totolOrderAmount) values(?,?,?)");
			pstmt.setInt(1, order.getOrderId());
			pstmt.setInt(2, order.getCustomerId());
			pstmt.setDouble(3, order.getTotolOrderAmount());
			int executeUpdate = pstmt.executeUpdate();
			if (executeUpdate == 1) {
				return 1;
			}
		} catch (SQLException e) {
			System.out.println("Error " + e.getMessage());
		}
		return 0;
	}

	@Override
	public int cancelOrder(int orderId) {
		try {
			pstmt = con.prepareStatement("DELETE from order where orderId =? ");
			pstmt.setInt(1, orderId);
			int executeUpdate = pstmt.executeUpdate();
			if (executeUpdate == 1) {
				return 1;
			}
		} catch (SQLException e) {
			System.out.println("Error " + e.getMessage());
		}
		return 0;
	}

	@Override
	public Order viewOrder(int orderId) {
	
		
		return null;
	}

	@Override
	public List<Order> viewallOrders(int customerId) {
//		List<Order> cartList=new ArrayList<Order>();	
//		try {
//			pstmt=con.prepareStatement("select * from cart where customerId =?");
//			pstmt.setInt(1, customerId);
//			rs=pstmt.executeQuery();
//			while (rs.next()) {
//				Cart cart=new Cart();			
//				cart.setCartId(rs.getInt("cartId"));
//				cart.setProductId(rs.getInt("productId"));
//				cart.setQuantity(rs.getInt("quantity"));
//				cart.setStatus(rs.getString("quantity"));
//				cart.setCustomerId(rs.getInt("customerId"));
//				cartList.add(cart);
//			}
//		} catch (SQLException e) {
//			System.out.println("error"+e.getMessage());
//		}catch (Exception e) {
//			System.out.println("error"+e.getMessage());
//		}
			return null;
	}
}
