package com.amaysim.shoppingCart.api;

import java.math.BigDecimal;
import java.util.List;

import com.amaysim.shoppingCart.dataObjects.Item;

public interface ShoppingCart {
	
	public void add(Item item);
	public void add(Item item, String promo);
	public BigDecimal getTotal();
	public List<Item> getItems();
	public void processItems();
	public String getPromoCode();
	
}
