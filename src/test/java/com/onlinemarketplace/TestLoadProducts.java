package com.onlinemarketplace;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.junit.Test;

import com.onlinemarketplace.businesslogic.PromotionalRulesService;
import com.onlinemarketplace.data.Item;
import com.onlinemarketplace.data.Products;

public class TestLoadProducts {
	
	  //Scale
	  private static int DECIMALS = 2;
	  private static RoundingMode ROUNDING_MODE = RoundingMode.UP;

	  private static double TOT_ITENS_VALUE = 74.20;
	  private static BigDecimal BASKET1 = new BigDecimal(66.78).setScale(DECIMALS, RoundingMode.DOWN);
	  private static BigDecimal BASKET2 = new BigDecimal(36.95).setScale(DECIMALS, RoundingMode.DOWN);
	  private static BigDecimal BASKET3 = new BigDecimal(73.76).setScale(DECIMALS, RoundingMode.DOWN);
	
    @Test public void testloadProducts() {
    	Products products = new Products();
    	List<Item> items = products.getAllItems();
        BigDecimal total = doRound(items
				.stream()
				.map(Item::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add));
        
        BigDecimal totExpected = new BigDecimal(TOT_ITENS_VALUE).setScale(DECIMALS, RoundingMode.DOWN);
        assertTrue(total.compareTo(totExpected) == 0);
        assertTrue("testloadProducts shold return true", items.size() == 3);
    }
    
    @Test public void testRules1() {
    	PromotionalRulesService itemOnRules = new PromotionalRulesService();
    	itemOnRules.addItem(new Item(001L,"Travel Card Holder",new BigDecimal(9.25)));
    	itemOnRules.addItem(new Item(002L,"Personalised cufflinks",new BigDecimal(45.00)));
    	itemOnRules.addItem(new Item(003L,"Kids T-shirt",new BigDecimal(19.95)));
   	
    	itemOnRules.applyRuleEngine();
    	BigDecimal totPrices = itemOnRules.getTotalPrice().setScale(DECIMALS, RoundingMode.UP);
    	BigDecimal totExpected = BASKET1;
    	assertTrue(totPrices.compareTo(totExpected) == 0);
    }
    
    @Test public void testRules2() {
    	PromotionalRulesService itemOnRules = new PromotionalRulesService();
    	itemOnRules.addItem(new Item(001L,"Travel Card Holder",new BigDecimal(9.25)));
    	itemOnRules.addItem(new Item(003L,"Kids T-shirt",new BigDecimal(19.95)));
    	itemOnRules.addItem(new Item(001L,"Travel Card Holder",new BigDecimal(9.25)));
    	
    	itemOnRules.applyRuleEngine();
    	BigDecimal totPrices = itemOnRules.getTotalPrice().setScale(DECIMALS, RoundingMode.UP);
    	BigDecimal totExpected = BASKET2;
    	assertTrue(totPrices.compareTo(totExpected) == 0);
    }
    
    @Test public void testRulesCase3() {
    	PromotionalRulesService itemOnRules = new PromotionalRulesService();
    	itemOnRules.addItem(new Item(001L,"Travel Card Holder",new BigDecimal(9.25)));
    	itemOnRules.addItem(new Item(002L,"Personalised cufflinks",new BigDecimal(45.00)));
    	itemOnRules.addItem(new Item(001L,"Travel Card Holder",new BigDecimal(9.25)));
    	itemOnRules.addItem(new Item(003L,"Kids T-shirt",new BigDecimal(19.95)));
    	
    	itemOnRules.applyRuleEngine();
    	BigDecimal totPrices = itemOnRules.getTotalPrice().setScale(DECIMALS, RoundingMode.UP);
    	BigDecimal totExpected = BASKET3;
    	assertTrue(totPrices.compareTo(totExpected) == 0);
    
    }
    
    @Test public void testRulesCase3DiferentInputOrder() {
    	PromotionalRulesService itemOnRules = new PromotionalRulesService();
    	itemOnRules.addItem(new Item(003L,"Kids T-shirt",new BigDecimal(19.95)));
    	itemOnRules.addItem(new Item(002L,"Personalised cufflinks",new BigDecimal(45.00)));
    	itemOnRules.addItem(new Item(001L,"Travel Card Holder",new BigDecimal(9.25)));
    	itemOnRules.addItem(new Item(001L,"Travel Card Holder",new BigDecimal(9.25)));
    	
    	itemOnRules.applyRuleEngine();
    	BigDecimal totPrices = itemOnRules.getTotalPrice().setScale(DECIMALS, RoundingMode.UP);
    	BigDecimal totExpected = BASKET3;
    	assertTrue(totPrices.compareTo(totExpected) == 0);
    
    }

    
    private BigDecimal doRound(BigDecimal number){
        return number.setScale(DECIMALS, ROUNDING_MODE);
    }
    
}
