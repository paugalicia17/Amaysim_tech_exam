package com.amaysim.shoppingCart;

import com.amaysim.shoppingCart.dataObjects.Item;
import com.amaysim.shoppingCart.impl.AmaysimPricingRules;
import com.amaysim.shoppingCart.impl.DisplayManager;
import com.amaysim.shoppingCart.impl.OrderManager;
import com.amaysim.shoppingCart.impl.ShoppingCartImpl;
import com.amaysim.shoppingCart.resources.ItemRepo;
import com.amaysim.shoppingCart.resources.PriceRuleRepo;
import com.amaysim.shoppingCart.service.UserInputService;

public class ShopAmaysim {

	private static PriceRuleRepo priceRepo = new PriceRuleRepo();
	private static ItemRepo itemRepo = new ItemRepo();
	
	private static UserInputService userInputService = new UserInputService();
	private static OrderManager orderMgr = new OrderManager();
	
	private static DisplayManager display;
	private static ShoppingCartImpl cart;
	
	private int itemNumber;
	private int itemQty;
	private String promoCode;
	private boolean addItems = true;
	
	public static void main(String[] args) {
		
		display = new DisplayManager(itemRepo);
		
		ShopAmaysim shop = new ShopAmaysim();
		shop.startShopping();

	}

	public void startShopping() {
		display.displayHeader();
		cart = new ShoppingCartImpl(new AmaysimPricingRules(priceRepo));
		
		while(addItems){
			display.displayProductList();
			try {
				startOrder();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		cart.processItems();
		display.displayOrders(cart);
	}

	public void startOrder() throws Exception{
		display.displayOrderItem();
		itemNumber = userInputService.getChoice();
		display.displayOrderQty();
		itemQty = userInputService.getChoice();
		display.displayPromo();
		promoCode = userInputService.getString();
		
		Item item = orderMgr.setOrder(itemRepo, itemNumber, itemQty);
		if(!promoCode.equalsIgnoreCase("")){
			cart.add(item, promoCode);
		} else {
			cart.add(item);
		}
		display.displayItemAddedToCart(itemQty, itemNumber);
		display.addAdditionalItems();
		addItems = (userInputService.getString().equalsIgnoreCase("Y")) ? true : false;
		
	}
}
