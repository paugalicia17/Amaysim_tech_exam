package com.amaysim.shoppingCart.api;

import java.util.List;

import com.amaysim.shoppingCart.dataObjects.BulkPricing;
import com.amaysim.shoppingCart.dataObjects.BundlePricing;
import com.amaysim.shoppingCart.dataObjects.BuyForPricing;

public interface PricingRules {

	public List<BulkPricing> getBulkRules();
	public List<BuyForPricing> getBuyForRules();
	public List<BundlePricing> getBundleRules();
}
