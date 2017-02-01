package com.amaysim.shoppingCart.resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.amaysim.shoppingCart.dataObjects.Discount;

public class DiscountRepo {

    private List<Discount> promoCodeList = new ArrayList<Discount>();
    
	public DiscountRepo(){
    	readPropertyFile();        
    }

    private void readPropertyFile(){
    	String fileName = "PromoCodes.properties";
    	
    	try{
    		InputStream fileInput;
    		Scanner scanner;
    		
    		fileInput = getClass().getClassLoader().getResourceAsStream(fileName);
    		
    		if(fileInput != null){
				scanner = new Scanner(fileInput);
			} else {
   				throw new FileNotFoundException("property file '" + fileName + "' not found in the classpath");
    		}
                       
            while(scanner.hasNextLine()){
            	setDiscountRules(scanner.nextLine());
            }
          
            scanner.close();
            
    	} catch (IOException ex){
    		ex.printStackTrace();
    	}
        
    }   
    	
    private void setDiscountRules(String promoRule) {
		Discount rule = new Discount();
		String[] parseRule = promoRule.split(",");
		
		rule.setPromoCode(parseRule[0]);
		rule.setAppliedTo(parseRule[1]);
		rule.setPercent(Double.valueOf(parseRule[2]));
		
		promoCodeList.add(rule);
	}

	public List<Discount> getPromoCodeList() {
		return promoCodeList;
	}

	public void setPromoCodeList(List<Discount> promoCodeList) {
		this.promoCodeList = promoCodeList;
	}
}
