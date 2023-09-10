package com.satish.model;

import java.util.List;

public class Order {
	private int orderId;
	private int customerId;
	private Double totolOrderAmount;
	private List<OrderProductInfo> productInfo;

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customerId=" + customerId + ", totolOrderAmount=" + totolOrderAmount
				+ ", productInfo=" + productInfo + ", getOrderId()=" + getOrderId() + ", getCustomerId()="
				+ getCustomerId() + ", getTotolOrderAmount()=" + getTotolOrderAmount() + ", getProductInfo()="
				+ getProductInfo() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Double getTotolOrderAmount() {
		return totolOrderAmount;
	}

	public void setTotolOrderAmount(Double totolOrderAmount) {
		this.totolOrderAmount = totolOrderAmount;
	}

	public List<OrderProductInfo> getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(List<OrderProductInfo> productInfo) {
		this.productInfo = productInfo;
	}

}
