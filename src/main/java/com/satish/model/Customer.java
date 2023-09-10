package com.satish.model;

public class Customer extends User{
	private String customerName;
	private String customerContact;
	private Address customerAddress;

	@Override
	public String toString() {
		return "Customer [customerName=" + customerName + ", customerContact=" + customerContact + ", customerAddress="
				+ customerAddress + "]";
	}
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}

	public Address getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(Address customerAddress) {
		this.customerAddress = customerAddress;
	}
}
