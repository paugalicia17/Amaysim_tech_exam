package com.amaysim.shoppingCart.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.amaysim.shoppingCart.api.PricingRules;
import com.amaysim.shoppingCart.api.ShoppingCart;
import com.amaysim.shoppingCart.dataObjects.Discount;
import com.amaysim.shoppingCart.dataObjects.Item;
import com.amaysim.shoppingCart.util.AmaysimConstants;

public class ShoppingCartImpl implements ShoppingCart {

	private PricingManager pricingMgr;
	private List<Item> itemList = new ArrayList<Item>();

	private String promoCode;
	private List<String> selectedItems = new ArrayList<String>();
	
	private BigDecimal totalPrice = BigDecimal.ZERO;	
	
	public ShoppingCartImpl(PricingRules pricingRules){
		this.pricingMgr = new PricingManager(pricingRules);
	}
	
	public void add(Item item) {
		processOrders(item);
	}

	public void add(Item item, String promo) {
		processOrders(item);
		setPromoCode(promo);
	}

	public BigDecimal getTotal() {
		for(Item item : itemList){
//			BigDecimal itemTotal = item.getPrice().multiply(BigDecimal.valueOf(item.getQty()));
			totalPrice = totalPrice.add(item.getPrice());
		}
		
		if(promoCode != null){
			Discount discRule = pricingMgr.getDiscountRules(promoCode);
			applyDiscount(discRule.getPercent(), discRule.getAppliedTo());
		}
		return totalPrice;
	}

	public List<Item> getItems() {
		return itemList;
	}

	public void processItems(){
		List<Item> finalItemList = new ArrayList<Item>();
		
		for(Item processedItem : itemList){
			List<String> applicableDeals = pricingMgr.getApplicableDeal(processedItem);
			
			for(String deal : applicableDeals){
				if(AmaysimConstants.BULK.equalsIgnoreCase(deal)){
					processedItem = pricingMgr.processBulkItem(processedItem);
				}
				if(AmaysimConstants.BUYFOR.equalsIgnoreCase(deal)){
					processedItem = pricingMgr.processBuyForItem(processedItem);
				}
				if(AmaysimConstants.BUNDLE.equalsIgnoreCase(deal)){
					Item bundleItem = pricingMgr.processBundleItem(processedItem);
					finalItemList.add(bundleItem);
				}
				if(!processedItem.isItemProcessed()){
					processedItem.setPrice(processedItem.getPrice().multiply(BigDecimal.valueOf(processedItem.getQty())));
				}
				finalItemList.add(processedItem);
			}
		}
		
		if(finalItemList == null || finalItemList.isEmpty()){
			finalItemList = getItems();
		}
		
		setItemList(finalItemList);
	}

	private void processOrders(Item item){
		String itemCode = item.getProductCode();
		
		if(selectedItems.contains(itemCode)){
			for(Item listItem : itemList){
				if(itemCode.equalsIgnoreCase(listItem.getProductCode())){
					listItem.setQty(listItem.getQty() + item.getQty());
				}
			}
		} else {
			selectedItems.add(itemCode);
			itemList.add(item);
		}
		
	}
	
	private void applyDiscount(double percent, String appliedTo){
		
		BigDecimal less = BigDecimal.ZERO;
		if(appliedTo.equalsIgnoreCase("FULL")) {
			less = totalPrice.multiply(BigDecimal.valueOf(percent)).divide(new BigDecimal("100"));
		}
		totalPrice = totalPrice.subtract(less);
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
}
