package com.satish.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.satish.dao.CartDao;
import com.satish.dao.ProductDao;
import com.satish.dbconnection.DbConnection;
import com.satish.model.Cart;

public class CartDaoImpl implements CartDao {
	Connection con = DbConnection.getDatabaseConnection();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ProductDao productDao=new ProductInfoDaoImpl();
	@Override
	public int addToCart(Cart cart) {
		try {
			int quantity = checkIfProductAlreadyAddedByCustomerToCart(cart.getProductId(), cart.getCustomerId());
			if (quantity == 0) {
				pstmt = con.prepareStatement("insert into cart (productId, quantity, status, customerId) values(?,?,?,?)");
				pstmt.setInt(1, cart.getProductId());
				pstmt.setInt(2, 1);
				pstmt.setString(3, cart.getStatus());
				pstmt.setInt(4, cart.getCustomerId());
				int executeUpdate = pstmt.executeUpdate();
				if (executeUpdate == 1) {
					return 1;
				}
			} else {
				cart.setQuantity(quantity + 1);
			return updateToCart(cart);
			}
		} catch (SQLException e) {
			System.out.println("Error " + e.getMessage());
		}
		return 0;
	}

	private int checkIfProductAlreadyAddedByCustomerToCart(int productId, int customerId) {
		try {
			pstmt = con.prepareStatement("select quantity from cart where productId=? and customerId=?");
			pstmt.setInt(1, productId);
			pstmt.setInt(2, customerId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("quantity");
			}

		} catch (SQLException e) {
			System.out.println("Error " + e.getMessage());
		}

		return 0;
	}
	
	@Override
	public int deletToCart(int cartId) {
		try {
			pstmt = con.prepareStatement("DELETE from cart where cartId =? ");
			pstmt.setInt(1, cartId);
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
	public int updateToCart(Cart cart) {
		try {
			pstmt = con.prepareStatement("update cart set  quantity= ? ,status=? where customerId =? and productId =?");
			pstmt.setInt(1, cart.getQuantity());
			pstmt.setString(2, cart.getStatus());
			pstmt.setInt(3, cart.getCustomerId());
			pstmt.setInt(4, cart.getProductId());
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
	public List<Cart> viewAllCart(int customerId) {
		List<Cart> cartList=new ArrayList<Cart>();	
		try {
			pstmt=con.prepareStatement("select * from cart where customerId =?");
			pstmt.setInt(1, customerId);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				Cart cart=new Cart();			
				cart.setCartId(rs.getInt("cartId"));
				cart.setProductId(rs.getInt("productId"));
				cart.setProduct(productDao.searchProduct(rs.getInt("productId")));
				cart.setQuantity(rs.getInt("quantity"));
				cart.setStatus(rs.getString("status"));
				cart.setCustomerId(rs.getInt("customerId"));
				cartList.add(cart);
			}
		} catch (SQLException e) {
			System.out.println("error"+e.getMessage());
		}catch (Exception e) {
			System.out.println("error"+e.getMessage());
		}
			return cartList;
	}
}
