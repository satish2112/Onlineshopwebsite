package com.satish.dao;

import java.util.List;

import com.satish.model.Cart;

public interface CartDao {
	int addToCart(Cart cart);
	int deletToCart(int cartId);
	int updateToCart(Cart cart);
	List<Cart> viewAllCart(int customerId);
	
}	
