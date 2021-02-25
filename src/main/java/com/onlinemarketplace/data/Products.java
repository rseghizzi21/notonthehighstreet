package com.onlinemarketplace.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ricardo
 * This class has the responsibility of loading items in productsInvenctoy and and fetch items 
 * from productsInvenctoy.  
 */
public class Products {
	
	private  List<Item> productsInventory = new ArrayList<Item>();

	
	public Products() {
		this.productsInventory = loadItems();
	}

	public Item getByCode(Long value) {
		Item resItem = productsInventory.stream()
		  .filter(c ->c.getProductCode().equals(value))
		  .findAny()           
		  .orElse(null); 
		
		return resItem;
	}
	
	public List<Item> getAllItems(){
		return productsInventory;
	}
	
	private List<Item> loadItems(){
		
		Item item1 = new Item(001L,"Travel Card Holder",new BigDecimal(9.25));
		productsInventory.add(item1);
		Item item2 = new Item(002L,"Personalised cufflinks",new BigDecimal(45.00));
		productsInventory.add(item2);
		Item item3 = new Item(003L,"Kids T-shirt",new BigDecimal(19.95));
		productsInventory.add(item3);
	
		return productsInventory;
	}
	
}
