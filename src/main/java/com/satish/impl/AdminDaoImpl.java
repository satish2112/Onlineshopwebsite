package com.satish.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.satish.dao.AdminDao;
import com.satish.dbconnection.DbConnection;

public class AdminDaoImpl implements AdminDao {
	Connection con=DbConnection.getDatabaseConnection(); 
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	@Override
	public boolean adminLogin(String username, String passwod) {
		try {
			pstmt= con.prepareStatement(" select *  from admin where username =? and password = ?");
				pstmt.setString(1, username);
				pstmt.setString(2, passwod);
				rs=	pstmt.executeQuery();
				if(rs.next()) {
					return true;
				}
		} catch (SQLException e) {
			System.out.println("probleam in syntext of query: "+e.getMessage());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	@Override
	public boolean updateAdminPassword(String usearname, String oldPassword, String newPassword) {	
			try {
				boolean isUserExsist=adminLogin(usearname, oldPassword);
					if(isUserExsist) {
						pstmt=con.prepareStatement("update admin set password=? where username=?");
						pstmt.setString(1, newPassword);
						pstmt.setString(2, usearname);
						int numberOfRowChange=pstmt.executeUpdate();
						if(numberOfRowChange==1) {
							return true;
						}
					}
			} catch (SQLException e) {
				System.out.println("Problem With query : "+e.getMessage());
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		return false;
	}
}