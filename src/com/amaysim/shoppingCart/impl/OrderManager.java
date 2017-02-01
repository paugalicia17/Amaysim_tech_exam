package com.amaysim.shoppingCart.impl;

import java.math.BigDecimal;

import com.amaysim.shoppingCart.dataObjects.Item;
import com.amaysim.shoppingCart.dataObjects.Products;
import com.amaysim.shoppingCart.resources.ItemRepo;

public class OrderManager {

	public Item setOrder(ItemRepo itemRepo,int index, int qty){
		Item item = new Item();
		
		for(Products product : itemRepo.getProductInfoList()){
			if(index == product.getIndex()){
				item.setProductCode(product.toString());
				item.setProductName(product.getName());
				item.setPrice(BigDecimal.valueOf(product.getPrice()));
				item.setQty(qty);
			}
		}
		
		return item;
		
	}
	
}
