package com.amaysim.shoppingCart.resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.amaysim.shoppingCart.dataObjects.BulkPricing;
import com.amaysim.shoppingCart.dataObjects.BundlePricing;
import com.amaysim.shoppingCart.dataObjects.BuyForPricing;

public class PriceRuleRepo {

    private List<BulkPricing> bulkRulesList = new ArrayList<BulkPricing>();
    private List<BundlePricing> bundleRulesList = new ArrayList<BundlePricing>();
    private List<BuyForPricing> buyForRulesList = new ArrayList<BuyForPricing>();
    
    public List<BulkPricing> getBulkRulesList() {
		return bulkRulesList;
	}

	public void setBulkRulesList(List<BulkPricing> bulkRulesList) {
		this.bulkRulesList = bulkRulesList;
	}

	public List<BundlePricing> getBundleRulesList() {
		return bundleRulesList;
	}

	public void setBundleRulesList(List<BundlePricing> bundleRulesList) {
		this.bundleRulesList = bundleRulesList;
	}

	public List<BuyForPricing> getBuyForRulesList() {
		return buyForRulesList;
	}

	public void setBuyForRulesList(List<BuyForPricing> buyForRulesList) {
		this.buyForRulesList = buyForRulesList;
	}

	public PriceRuleRepo(){
    	readPropertyFile();        
    }

    private void readPropertyFile(){
    	Properties props = new Properties();
		InputStream fileInput = null;
    	
		try {
			String fileName = "AmaysimPriceRules.properties";
			fileInput = getClass().getClassLoader().getResourceAsStream(fileName);
			if(fileInput != null){
				props.load(fileInput);
			} else {
				throw new FileNotFoundException("property file '" + fileName + "' not found in the classpath");
			}
			
			String bulkRules = props.getProperty("BULK");
			String bundleRules = props.getProperty("BUNDLE");
			String buyForRules = props.getProperty("BUYFOR");
			
			setRules(bulkRules, bundleRules, buyForRules);
		
		} catch (IOException ex) {
			ex.printStackTrace();
	    } finally{
	    	if(fileInput!=null){
	    		try {
	    			fileInput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	}
	    }
    }

	private void setRules(String bulk, String bundle, String buyFor) {
		String[] bulkRules = bulk.split(";");
		String[] bundleRules = bundle.split(";");
		String[] buyForRules = buyFor.split(";");
		
		if(bulkRules != null){
			for(int i = 0; bulkRules.length > i; i++){
				BulkPricing bulkPricing = new BulkPricing(bulkRules[i]);
				bulkRulesList.add(bulkPricing);
			}
		}
		
		if(bundleRules != null){
			for(int i = 0; bundleRules.length > i; i++){
				BundlePricing bundlePricing = new BundlePricing(bundleRules[i]);
				bundleRulesList.add(bundlePricing);
			}
		}
		
		if(buyForRules != null){
			for(int i = 0; buyForRules.length > i; i++){
				BuyForPricing buyForPricing = new BuyForPricing(buyForRules[i]);
				buyForRulesList.add(buyForPricing);
			}
		}
	}
}
