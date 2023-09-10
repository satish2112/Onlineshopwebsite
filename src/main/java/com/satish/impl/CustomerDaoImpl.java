package com.satish.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.satish.dao.AddressDao;
import com.satish.dao.CustomerDao;
import com.satish.dbconnection.DbConnection;
import com.satish.model.Address;
import com.satish.model.Customer;

public class CustomerDaoImpl implements CustomerDao {
	Connection con = DbConnection.getDatabaseConnection();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	AddressDao addressDao = new AddressDaoImpl();

	@Override
	public int addCustomer(Customer customer) {
		try {
			int autoGeneratedAddressId = addressDao.addAddress(customer.getCustomerAddress());
			if (autoGeneratedAddressId != -1) {
				pstmt = con.prepareStatement(
						"insert into customer(username,password,customerName,customerContact,addressid) values(?,?,?,?,?)");
				pstmt.setString(1, customer.getUsername());
				pstmt.setString(2, customer.getPassword());
				pstmt.setString(3, customer.getCustomerName());
				pstmt.setString(4, customer.getCustomerContact());
				pstmt.setInt(5, autoGeneratedAddressId);

				int executeUpdate = pstmt.executeUpdate();
				if (executeUpdate == 1) {
					return 1;
				}
			}
		} catch (SQLException e) {
			System.out.println("Proble in sql : " + e.getMessage());
		}
		return 0;
	}
	
	@Override
	public boolean customerLogin(String username, String password) {
		try {
			pstmt = con.prepareStatement("SELECT * FROM customer where username=? and password=?");
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override  
	public int updateAddress(Address custAddress) {
		return addressDao.updateAddress(custAddress);
	}

	private int checkIfCustomerAlreadyRegister(String username, String oldPassword) {
		try {
			pstmt = con.prepareStatement("select userid from customer where username=? and password=?");
			pstmt.setString(1, username);
			pstmt.setString(2, oldPassword);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("userid");
			}
		} catch (SQLException e) {
			System.out.println("Error " + e.getMessage());
		}
		return 0;
	}

	@Override
	public int updateCustomerDetails(Customer customer) {
		try {
			pstmt = con.prepareStatement("UPDATE customer set  customerName= ?, customerContact=? where username =?");
			pstmt.setString(1, customer.getCustomerName());
			pstmt.setString(2, customer.getCustomerContact());
			pstmt.setString(3, customer.getUsername());
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
	public boolean updateCustomerPassword(String usearname, String oldPassword, String newPassword) {
		try {
			int isUserExsist = checkIfCustomerAlreadyRegister(usearname, oldPassword);
			if (isUserExsist != 0) {
				pstmt = con.prepareStatement("update customer set password=? where userid=?");
				pstmt.setString(1, newPassword);
				pstmt.setInt(2, isUserExsist);
				int numberOfRowChange = pstmt.executeUpdate();
				if (numberOfRowChange == 1) {
					return true;
				}
			} else {
				System.out.println("Register first");
			}
		} catch (SQLException e) {
			System.out.println("Problem With query : " + e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override 			  
	public List<Customer> viewAllCustomers() {
		List<Customer> customersList = new ArrayList<Customer>();
		try {
			pstmt = con.prepareStatement("select * from customer");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setUserId(rs.getInt("userid"));
				customer.setUsername(rs.getString("username"));
				customer.setCustomerName(rs.getString("customerName"));
				customer.setCustomerContact(rs.getString("customerContact"));
				Address customerAddress = addressDao.getAddress(rs.getInt("addressid"));
				customer.setCustomerAddress(customerAddress);
				customersList.add(customer);
			}
		} catch (SQLException e) {
			System.out.println("error" + e.getMessage());
		} catch (Exception e) {
			System.out.println("error" + e.getMessage());
		}

		return customersList;
	}
	
	
	@Override
	public Customer viewCustomer(String username) {
		Customer customer=null;
		try {
			pstmt=con.prepareStatement("SELECT * FROM customer where username=?");
			pstmt.setString(1, username);
			rs=pstmt.executeQuery();
			
			if(rs.next()) { 
				customer=new Customer();
				customer.setUserId(rs.getInt("userid"));
				customer.setUsername(rs.getString("username"));
				customer.setCustomerName(rs.getString("customerName"));
				customer.setCustomerContact(rs.getString("customerContact"));
				Address customerAddress=addressDao.getAddress(rs.getInt("addressid"));
				customer.setCustomerAddress(customerAddress);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}


}