package com.amaysim.shoppingCart.util;

public class PrintUtil {

	public static void print(String output){
		System.out.print(output);
	}
	
	public static void printNextLine(String output){
		System.out.println(output);
	}
	
	public static void printOrderSummaryHeader(){
		System.out.println("****************************************");
		System.out.println("*             ORDER SUMMARY            *"); 
		System.out.println("****************************************");
		System.out.println(" PRODUCT	  QTY           PRICE  ");
		System.out.println("========================================");
	}
}
