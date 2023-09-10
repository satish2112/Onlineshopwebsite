package com.satish.dao;

import com.satish.model.Address;

public interface AddressDao {
	int addAddress (Address address );
	int updateAddress(Address addresss);
//	int deleteAddress(int customerId); not conform yet
	Address getAddress(int customerId);
}
