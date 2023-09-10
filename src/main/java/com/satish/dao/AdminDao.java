package com.satish.dao;

public interface AdminDao {
	boolean adminLogin(String username, String passwod);
	boolean updateAdminPassword(String usearname,String oldPassword,String newPassword);

}
