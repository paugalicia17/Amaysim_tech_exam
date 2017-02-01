package com.amaysim.shoppingCart.dataObjects;

import java.math.BigDecimal;


public class BuyForPricing {

	private String productCode;
	private BigDecimal price;
	private int startingQty;
	private int priceQty;
	
	public BuyForPricing(String rule){
		String[] ruleArray = rule.split(",");
		setStartingQty(Integer.valueOf(ruleArray[0]));
		setPriceQty(Integer.valueOf(ruleArray[1]));
		setProductCode(ruleArray[2]);
		setPrice(BigDecimal.valueOf(Double.valueOf(ruleArray[3])));
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

	public int getPriceQty() {
		return priceQty;
	}

	public void setPriceQty(int priceQty) {
		this.priceQty = priceQty;
	}

	public int getStartingQty() {
		return startingQty;
	}

	public void setStartingQty(int startingQty) {
		this.startingQty = startingQty;
	}
}
