package com.satish.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.satish.dao.OrderProductInfoDao;
import com.satish.dbconnection.DbConnection;
import com.satish.model.OrderProductInfo;

public class OrderProductInfoDaoImpl implements OrderProductInfoDao {
	Connection con = DbConnection.getDatabaseConnection();
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@Override
	public int addOrderProductInfoId(List<OrderProductInfo> orderProductInfo, int orderId) {
		try {
			int numberOfRowsInserted=0;
			for(OrderProductInfo eachProduct:orderProductInfo) {
				
				pstmt=con.prepareStatement("INSERT INTO order_product_info (productId,quantity,subTotal,orderId) "
						+ "VALUES "
						+ "(?,?,?,?)");
				pstmt.setInt(1, eachProduct.getProductId());
				pstmt.setInt(2, eachProduct.getQuantity());
				pstmt.setDouble(3, eachProduct.getSubTotal());
				pstmt.setInt(4, orderId);
				numberOfRowsInserted=numberOfRowsInserted+pstmt.executeUpdate();
			}
			
			if(numberOfRowsInserted==orderProductInfo.size()) {
				return 1;
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return 0;
	}
	@Override
	public List<OrderProductInfo> viewProductByOrderId(int orderId) {
		List<OrderProductInfo> listOfOrderProducts=new ArrayList<>();
		try {
			pstmt=con.prepareStatement("SELECT * FROM order_product_info where orderId=?");
			pstmt.setInt(1, orderId);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {  
				OrderProductInfo productRecord=new OrderProductInfo();
				
				productRecord.setOrderId(orderId);
				productRecord.setOrderProductInfoId(rs.getInt("orderProductInfoId"));
				productRecord.setProductId(rs.getInt("productId"));
				productRecord.setQuantity(rs.getInt("quantity"));
				productRecord.setSubTotal(rs.getDouble("subTotal"));
				listOfOrderProducts.add(productRecord);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return listOfOrderProducts;
	}
}
