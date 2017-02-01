package com.amaysim.shoppingCart.impl;

import java.util.List;

import com.amaysim.shoppingCart.api.PricingRules;
import com.amaysim.shoppingCart.dataObjects.BulkPricing;
import com.amaysim.shoppingCart.dataObjects.BundlePricing;
import com.amaysim.shoppingCart.dataObjects.BuyForPricing;
import com.amaysim.shoppingCart.resources.PriceRuleRepo;

public class AmaysimPricingRules implements PricingRules {

	private List<BulkPricing> bulkRulesList;
	private List<BundlePricing> bundleRulesList;
	private List<BuyForPricing> buyForRulesList;
	
	public AmaysimPricingRules(PriceRuleRepo ruleRepo){
		this.bulkRulesList = ruleRepo.getBulkRulesList();
		this.bundleRulesList = ruleRepo.getBundleRulesList();
		this.buyForRulesList = ruleRepo.getBuyForRulesList();
	}

	public  List<BulkPricing> getBulkRules() {
		return bulkRulesList;		
	}

	public  List<BuyForPricing> getBuyForRules() {
		return buyForRulesList;
	}

	public  List<BundlePricing> getBundleRules() {
		return bundleRulesList;
	}

}
