package com.satish.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.satish.dao.AddressDao;
import com.satish.dbconnection.DbConnection;
import com.satish.model.Address;

public class AddressDaoImpl  implements AddressDao{
	Connection con=DbConnection.getDatabaseConnection(); 
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	@Override
	public int addAddress(Address address) {
		try {
			String query=" insert into address (addressLine1, addressLine2, pincode, city, state) values(?,?,?,?,?)";
			
			pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, address.getAddressLine1());
			pstmt.setString(2, address.getAddressLine2());
			pstmt.setString(3, address.getPincode());
			pstmt.setString(4, address.getCity());
			pstmt.setString(5, address.getState());
			
			pstmt.executeUpdate();
			rs=pstmt.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	@Override
	public int updateAddress(Address addresss) {
		try {
			pstmt = con.prepareStatement("UPDATE address set  addressLine1= ?, addressLine2=?, pincode=?, city=?, state=? where addressId =?");
			pstmt.setString(1, addresss.getAddressLine1());
			pstmt.setString(2, addresss.getAddressLine2());
			pstmt.setString(3, addresss.getPincode());
			pstmt.setString(4, addresss.getCity());
			pstmt.setString(5, addresss.getState());
			pstmt.setInt(6, addresss.getAddressId());
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
	public Address getAddress(int customerId) {
		  Address address=new Address();
		try {
			pstmt=con.prepareStatement("select * from address where addressId =?");
			pstmt.setInt(1,customerId);
			rs=pstmt.executeQuery();
			if (rs.next()) {
			  address.setAddressId(rs.getInt("addressId"));
			  address.setAddressLine1(rs.getString("addressLine1"));
			  address.setAddressLine2(rs.getString("addressLine1"));
			  address.setPincode(rs.getString("pincode"));
			  address.setCity(rs.getString("city"));
			  address.setState(rs.getString("state"));
			} 
		} catch (SQLException e) {
			System.out.println("problem in query"+e.getMessage());
		}
		return address;
		
	}

}
