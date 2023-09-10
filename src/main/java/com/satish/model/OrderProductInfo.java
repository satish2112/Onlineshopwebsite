package com.satish.model;

public class OrderProductInfo {
	private int orderProductInfoId;
	private int productId;
	private int quantity;
	private double subTotal;
	private int orderId;
	

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderProductInfoId() {
		return orderProductInfoId;
	}

	public void setOrderProductInfoId(int orderProductInfoId) {
		this.orderProductInfoId = orderProductInfoId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "OrderProductInfo [orderProductInfoId=" + orderProductInfoId + ", productId=" + productId + ", quantity="
				+ quantity + ", subTotal=" + subTotal + ", orderId=" + orderId + "]";
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
}
