package com.onlinemarketplace.data;

import java.math.BigDecimal;


/**
 * @author ricardo
 * Simple POJO representing and Item in the system.
 */
public class Item {

	private Long productCode;
	private String name;
	private BigDecimal price;
	
	
	public Item(Long productCode, String name, BigDecimal price) {
		super();
		this.productCode = productCode;
		this.name = name;
		this.price = price;
	}
	
	public Long getProductCode() {
		return productCode;
	}
	public void setProductCode(Long productCode) {
		this.productCode = productCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}

