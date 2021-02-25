package com.onlinemarketplace.businesslogic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.onlinemarketplace.data.Item;

/**
 * @author ricardo
 * This class is the core of the system and has a pseudo rule engine(method applyRuleEngine()) and to methods 
 * with the rules defined in methods and hard coded. Very primitive indeed. It can be improved a lot injecting the rules from outside(from file system, database table)
 * and creating a mechanism to execute this rules. Or the rules maybe could be defined  as a predicates and applied.   
 */
public class PromotionalRulesService {
	
	private static final  Long RULE_CAR_HOLDER = new Long(001L);
	private static final  BigDecimal DISCOUNTED_PRICE = new BigDecimal(8.5d);
	public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
	private static final BigDecimal DISCOUNT_PERCENTAGE = new BigDecimal(10);
	private static final BigDecimal SPEND_OVER_LIMIT = new BigDecimal(60);
	
	private List<Item> itemList = new ArrayList<Item>();
	private List<Item> discountedList = new ArrayList<Item>();

	private BigDecimal totalPrice = new BigDecimal(0.0d);
	
	public void addItem(Item item) {
		itemList.add(item);
	}
	
	public List<Item> applyRuleEngine() {

		applyTravelCarHolderRule();
        applyTotalPriceRule();
        return discountedList;
		
	}

	private void applyTravelCarHolderRule() {	
		long rule1count = itemList.stream()
		  .filter(i -> i.getProductCode().equals(RULE_CAR_HOLDER))
		  .count();
		
		if(rule1count >= 2)	{
			for(Item i:itemList) {
				if(i.getProductCode().equals(RULE_CAR_HOLDER)) { 
					Item discItem = i;
					discItem.setPrice(DISCOUNTED_PRICE);
					discountedList.add(discItem);
				} else {
					discountedList.add(i);
				}	
			}
		}else {
			discountedList = itemList;
		}	
	}

	private void applyTotalPriceRule() {
		BigDecimal acumPrice = discountedList.stream()
                .map(x -> x.getPrice()) 
                .reduce(BigDecimal.ZERO, BigDecimal::add); 
		if(acumPrice.compareTo(SPEND_OVER_LIMIT) > 0) {
			BigDecimal promoPercentage = calcPercentage(acumPrice, DISCOUNT_PERCENTAGE);
			totalPrice = acumPrice.subtract(promoPercentage);
		} else {
			totalPrice = acumPrice;
		}
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}


	public static BigDecimal calcPercentage(BigDecimal value, BigDecimal pct){
	    return value.multiply(pct).divide(ONE_HUNDRED);
	}
	
}
