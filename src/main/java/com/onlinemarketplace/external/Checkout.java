package com.onlinemarketplace.external;

import java.math.RoundingMode;
import com.onlinemarketplace.businesslogic.PromotionalRulesService;
import com.onlinemarketplace.data.Item;

/**
 * @author ricardo
 * System interface to the outside world. As defined in the specification. Doesn't do to much.
 * It works  as a wrapper to the underling PromotionalRulesService.
 */
public class Checkout {
	//Scale
	private static int DECIMALS = 2;
	private PromotionalRulesService promotionalRulesService;

	public Checkout(PromotionalRulesService promotionalRulesService) {
		this.promotionalRulesService = promotionalRulesService;
	}
	
	public void scan(Item item) {
		promotionalRulesService.addItem(item);
	}
	
	public Double total() {
		promotionalRulesService.applyRuleEngine();
		return promotionalRulesService.getTotalPrice().setScale(DECIMALS, RoundingMode.UP).doubleValue();
	
	}
	
}
