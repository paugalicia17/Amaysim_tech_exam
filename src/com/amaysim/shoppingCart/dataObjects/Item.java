package com.amaysim.shoppingCart.dataObjects;

import java.math.BigDecimal;

public class Item {

	private String productCode;
	private String productName;
	private BigDecimal price;
	private int qty;
	private boolean isItemProcessed = false;
	
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public boolean isItemProcessed() {
		return isItemProcessed;
	}
	public void setItemProcessed(boolean isItemProcessed) {
		this.isItemProcessed = isItemProcessed;
	}
	
}
