package com.amaysim.shoppingCart.dataObjects;

import java.math.BigDecimal;

public class BulkPricing {

	private String productCode;
	private BigDecimal price;
	private int startingQty;
	
	public BulkPricing(String rule){
		String[] ruleArray = rule.split(",");
		this.startingQty = Integer.valueOf(ruleArray[0]);
		this.productCode = ruleArray[1];
		this.price = BigDecimal.valueOf(Double.valueOf(ruleArray[2]));
	}
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getStartingQty() {
		return startingQty;
	}
	public void setStartingQty(int startingQty) {
		this.startingQty = startingQty;
	}
}
