package com.amaysim.shoppingCart.impl;

import java.util.EnumSet;
import java.util.List;

import com.amaysim.shoppingCart.api.ShoppingCart;
import com.amaysim.shoppingCart.dataObjects.Item;
import com.amaysim.shoppingCart.dataObjects.Products;
import com.amaysim.shoppingCart.resources.ItemRepo;
import com.amaysim.shoppingCart.util.AmaysimConstants;
import com.amaysim.shoppingCart.util.PrintUtil;

public class DisplayManager {
		
	private EnumSet<Products> productInfoList;
	private List<String> itemList;
	
	public DisplayManager(ItemRepo itemRepo){
		productInfoList = itemRepo.getProductInfoList();
		itemList = itemRepo.getItemList();
	}
	
	public void displayProductList(){
		PrintUtil.printNextLine(AmaysimConstants.ORDER_HEADER);
		
		for(Products product : productInfoList){
			
			PrintUtil.print("[" + product.getIndex() + "]" + AmaysimConstants.SPACING);
			PrintUtil.print(product.getName().toString() + AmaysimConstants.SPACING);
			PrintUtil.printNextLine(AmaysimConstants.DOLLAR_SIGN + String.valueOf(product.getPrice()));
		
		}
	}
	
	public void displayHeader(){
		PrintUtil.printNextLine(AmaysimConstants.CART_HEADER);
	}
	
	public void displayOrderItem(){
		PrintUtil.printNextLine(AmaysimConstants.WHAT_ORDER);
	}
	
	public void displayOrderQty(){
		PrintUtil.printNextLine(AmaysimConstants.ORDER_QTY);
	}

	public void displayOrders(ShoppingCart cartItems) {
		PrintUtil.printOrderSummaryHeader();
		int totalQty = 0;
		
		for(Item item : cartItems.getItems()){

			totalQty = totalQty + item.getQty();
			
			PrintUtil.print(item.getProductName() + "      ");
			PrintUtil.print(item.getQty() + "            ");
			PrintUtil.printNextLine(AmaysimConstants.DOLLAR_SIGN + item.getPrice().toString());
		}
		PrintUtil.printNextLine("========================================");
		if(cartItems.getPromoCode() != null){
			PrintUtil.printNextLine("PROMO CODE: " + cartItems.getPromoCode());
		}
		PrintUtil.printNextLine("TOTAL:             "+ totalQty +"            $" + cartItems.getTotal().toString());
	}

	public void addAdditionalItems() {
		PrintUtil.printNextLine(AmaysimConstants.ADD_ORDER);
		
	}
	
	public void displayPromo() {
		PrintUtil.printNextLine(AmaysimConstants.PROMO_CODE);
	}
	
	public void displayItemAddedToCart(int qty, int item){
		PrintUtil.printNextLine("-------> " + AmaysimConstants.ITEM_ADDED_MSG + qty + " " + itemList.get(item-1) + "!");
	}
}
