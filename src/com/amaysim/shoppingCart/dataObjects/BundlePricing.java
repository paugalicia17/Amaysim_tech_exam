package com.amaysim.shoppingCart.dataObjects;


public class BundlePricing {

	private String productCode;
	private String bundleProduct;
	
	public BundlePricing(String rule){
		String[] ruleArray = rule.split(",");
		this.productCode = ruleArray[0].toUpperCase();
		this.bundleProduct = ruleArray[1].toUpperCase();
	}
	
	public String getProductCode() {
		return productCode;
	}
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getBundleProduct() {
		return bundleProduct;
	}

	public void setBundleProduct(String bundleProduct) {
		this.bundleProduct = bundleProduct;
	}
}
