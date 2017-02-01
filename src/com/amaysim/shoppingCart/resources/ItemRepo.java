package com.amaysim.shoppingCart.resources;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.amaysim.shoppingCart.dataObjects.Products;

public class ItemRepo {

	private EnumSet<Products> productInfoList;
	private List<String> itemList = new ArrayList<String>(10);
	
	public ItemRepo(){
		setProductInfoList(EnumSet.allOf(Products.class));
		setItemList();
	}

	private void setItemList() {
		for(Products product : productInfoList){
			itemList.add(product.getIndex()-1, product.getName());
		}
	}

	public EnumSet<Products> getProductInfoList() {
		return productInfoList;
	}

	public void setProductInfoList(EnumSet<Products> productInfoList) {
		this.productInfoList = productInfoList;
	}

	public List<String> getItemList() {
		return itemList;
	}
}
