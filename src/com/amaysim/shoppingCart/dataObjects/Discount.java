package com.amaysim.shoppingCart.dataObjects;

public class Discount {

	private String promoCode;
	private String appliedTo;
	private double percent;
	
	public String getPromoCode() {
		return promoCode;
	}
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}
	public String getAppliedTo() {
		return appliedTo;
	}
	public void setAppliedTo(String appliedTo) {
		this.appliedTo = appliedTo;
	}
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}
}
