package com.satish.dao;

import java.util.List;

import com.satish.model.Address;
import com.satish.model.Customer;

public interface CustomerDao {
	/*int represents if customer added or not*/
	int addCustomer(Customer customer);
	boolean customerLogin(String username,String password);
	int updateAddress(Address custAddress);
	int updateCustomerDetails(Customer customer);
	boolean updateCustomerPassword(String username,String oldPassword,String newPassword);
	List<Customer> viewAllCustomers();
	Customer viewCustomer(String username);
}
