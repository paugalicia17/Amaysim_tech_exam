package com.amaysim.shoppingCart.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.amaysim.shoppingCart.api.PricingRules;
import com.amaysim.shoppingCart.dataObjects.BulkPricing;
import com.amaysim.shoppingCart.dataObjects.BundlePricing;
import com.amaysim.shoppingCart.dataObjects.BuyForPricing;
import com.amaysim.shoppingCart.dataObjects.Discount;
import com.amaysim.shoppingCart.dataObjects.Item;
import com.amaysim.shoppingCart.dataObjects.Products;
import com.amaysim.shoppingCart.resources.DiscountRepo;
import com.amaysim.shoppingCart.util.AmaysimConstants;

public class PricingManager {

	DiscountRepo discountRepo = new DiscountRepo();
	
	private List<BulkPricing> bulkRulesList;
	private List<BundlePricing> bundleRulesList;
	private List<BuyForPricing> buyForRulesList;
	private List<Discount> discountCodesList;
	
	private List<String> bulkItems = new ArrayList<String>();
	private List<String> bundleItems = new ArrayList<String>();
	private List<String> buyForItems = new ArrayList<String>();
	
	public PricingManager(PricingRules priceRules){
		setBulkRulesList(priceRules.getBulkRules());
		setBundleRulesList(priceRules.getBundleRules());
		setBuyForRulesList(priceRules.getBuyForRules());
		
		setItemsPerRule();
		discountCodesList = discountRepo.getPromoCodeList();
		
	}

	public Discount getDiscountRules(String promoCode) {
		Discount promoDisc = new Discount();
		
		for(Discount disc : discountCodesList){
			if(promoCode.equalsIgnoreCase(disc.getPromoCode())){
				promoDisc = disc;
			}
		}
		return promoDisc;
	}

	private void setItemsPerRule() {
		for(BulkPricing bulkRule : bulkRulesList){
			bulkItems.add(bulkRule.getProductCode().toUpperCase());
		}
		
		for(BundlePricing bundleRule : bundleRulesList){
			bundleItems.add(bundleRule.getProductCode().toUpperCase());
		}
		
		for(BuyForPricing buyForRule : buyForRulesList){
			buyForItems.add(buyForRule.getProductCode().toUpperCase());
		}
	}

	public List<BulkPricing> getBulkRulesList() {
		return bulkRulesList;
	}

	public void setBulkRulesList(List<BulkPricing> bulkRulesList) {
		this.bulkRulesList = bulkRulesList;
	}

	public List<BundlePricing> getBundleRulesList() {
		return bundleRulesList;
	}

	public void setBundleRulesList(List<BundlePricing> bundleRulesList) {
		this.bundleRulesList = bundleRulesList;
	}

	public List<BuyForPricing> getBuyForRulesList() {
		return buyForRulesList;
	}

	public void setBuyForRulesList(List<BuyForPricing> buyForRulesList) {
		this.buyForRulesList = buyForRulesList;
	}
	
	public List<String> getApplicableDeal(Item item){
		List<String> applicableRule = new ArrayList<String>();
		String itemCode = item.getProductCode();
		
		if(!bulkItems.isEmpty() && bulkItems.contains(itemCode)){
			applicableRule.add(AmaysimConstants.BULK);
		} 
		
		if(!bundleItems.isEmpty() && bundleItems.contains(itemCode)){
			applicableRule.add(AmaysimConstants.BUNDLE);
		}
		
		if(!buyForItems.isEmpty() && buyForItems.contains(itemCode)){
			applicableRule.add(AmaysimConstants.BUYFOR);
		}
		
		return applicableRule;
	}
	
	public Item processBulkItem(Item item){
		String itemCode = item.getProductCode();
		Item bulkItem = item;
		
		for(BulkPricing bulkRule : bulkRulesList){
			if(bulkRule.getProductCode().equalsIgnoreCase(itemCode)){
				if(item.getQty() >= bulkRule.getStartingQty()){
					BigDecimal newTotalPrice = bulkRule.getPrice().multiply(BigDecimal.valueOf(item.getQty()));
					bulkItem.setPrice(newTotalPrice);
					bulkItem.setItemProcessed(true);
				}
			}
		}
		return bulkItem;
	}
	
	public Item processBuyForItem(Item item){
		String itemCode = item.getProductCode();
		Item buyForItems = item;
		
		for(BuyForPricing buyForRule : buyForRulesList){
			if(buyForRule.getProductCode().equalsIgnoreCase(itemCode)){
				if(item.getQty()== buyForRule.getStartingQty()){
					BigDecimal newTotalPrice = buyForRule.getPrice().multiply(BigDecimal.valueOf(buyForRule.getPriceQty()));
					buyForItems.setPrice(newTotalPrice);
					buyForItems.setItemProcessed(true);
				}
			}
		}
		
		return buyForItems;
	}

	public Item processBundleItem(Item item) {
		String itemCode = item.getProductCode();
		Item bundleItem = new Item();
		
		for(BundlePricing bundleRule : bundleRulesList){
			if(bundleRule.getProductCode().equalsIgnoreCase(itemCode)){
				
				bundleItem.setProductCode(Products.valueOf(bundleRule.getBundleProduct()).toString());
				bundleItem.setProductName(Products.valueOf(bundleRule.getBundleProduct()).getName());
				bundleItem.setPrice(BigDecimal.ZERO);
				bundleItem.setQty(item.getQty());
				bundleItem.setItemProcessed(true);
			}
		}
		return bundleItem;
	}
}
