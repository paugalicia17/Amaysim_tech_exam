package com.amaysim.shoppingCart.service;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.amaysim.shoppingCart.util.AmaysimConstants;

/**
 * The UserInputService represents a service object that handles user input transactions 
 *
 */
public class UserInputService {
	
	Scanner scan = new Scanner(System.in);
	
	/**
	 * This method returns an integer from the user input
	 * @return an integer
	 * @throws Exception 
	 */
	public int getInt() throws Exception {
		int num = 0;
		try {
			num = scan.nextInt();
		} catch (InputMismatchException e) {
			num  = 0;
			throw new Exception("\t[!]" + AmaysimConstants.INCORRECT_INPUT_MSG, e);
		}
		return num;
	}
	
	/**
	 * This method returns a string from the user input
	 * @return a string
	 */
	public String getString() {
		return scan.nextLine();
	}
	
	/**
	 * This method returns the choice of the user
	 * @return the choice of the user
	 * @throws Exception 
	 */
	public int getChoice() throws Exception {
		int choice = 0;
		try {
			choice = Integer.parseInt(getString());
		} catch (NumberFormatException e) {
			choice = 0;
			throw new Exception("\t[!] " + AmaysimConstants.INCORRECT_INPUT_MSG, e);
		}
		return choice;
	}
}
