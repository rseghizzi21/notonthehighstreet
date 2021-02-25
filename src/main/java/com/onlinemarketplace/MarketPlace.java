package com.onlinemarketplace;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import com.onlinemarketplace.businesslogic.PromotionalRulesService;
import com.onlinemarketplace.data.Item;
import com.onlinemarketplace.data.Products;
import com.onlinemarketplace.external.Checkout;
/**
 * @author ricardo
 * 
 */
public class MarketPlace {

	private static Products products = new Products();
	
	public static void main(String[] args) {
		// Populate baskets
		List<Item> basket1 = fillBasketByCode(Arrays.asList(001L, 002L, 003L));
		List<Item> basket2 = fillBasketByCode(Arrays.asList(001L, 003L, 001L));
		List<Item> basket3 = fillBasketByCode(Arrays.asList(001L, 002L,001L,003L));
		// exta baskets for more tests
		List<Item> basket4 = fillBasketByCode(Arrays.asList(003L, 003L,003L));
		List<Item> basket5 = fillBasketByCode(Arrays.asList(001L, 001L,001L));
		
		Checkout checkout = new Checkout(new PromotionalRulesService());
		for(Item item : basket1) 
			checkout.scan(item);
		System.out.println("Basket 1: " + checkout.total());
		
		checkout = new Checkout(new PromotionalRulesService());
		for(Item item : basket2) 
			 checkout.scan(item); 
		System.out.println("Basket 2: "+ checkout.total());
		
		checkout = new Checkout(new PromotionalRulesService());
		for(Item item : basket3) 
			 checkout.scan(item); 
		System.out.println("Basket 3: "+ checkout.total());
		
		checkout = new Checkout(new PromotionalRulesService());
		for(Item item : basket4) 
			 checkout.scan(item); 
		System.out.println("Basket 4: "+ checkout.total());
		
		checkout = new Checkout(new PromotionalRulesService());
		for(Item item : basket5) 
			 checkout.scan(item); 
		System.out.println("Basket 5: "+ checkout.total());
	}

	
	static List<Item> fillBasketByCode(List<Long> productCodes){
		List<Item> items = new ArrayList<Item>();
		for(Long code : productCodes) {
			//In real system this should result in an IO operation. I protect with an try/catch
			// and fail logging here.
			try {
				items.add(products.getByCode(code));				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
			
		return items;
	}
}
