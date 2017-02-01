package com.amaysim.shoppingCart.dataObjects;

public enum Products {

	ONE_GB("1GB Data-Pack",9.90,1),
	ULT_SMALL("Unlimited 1GB",24.90,2),
	ULT_MEDIUM("Unlimited 2GB",29.90,3),
	ULT_LARGE("Unlimited 5GB",44.90,4);
	
	private final String name;   // product name
    private final double price;
    private final int index;
    
    Products(String name, double price, int index) {
        this.name = name;
        this.price = price;
        this.index = index;
    }
    
    public static final String[] productNames = new String[values().length];
    static {
        Products[] values = values();
        for(int i=0; i<values.length; i++){
        	productNames[i] = values[i].name();
        } 	
    }

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
	
	public int getIndex() {
		return index;
	}
}
