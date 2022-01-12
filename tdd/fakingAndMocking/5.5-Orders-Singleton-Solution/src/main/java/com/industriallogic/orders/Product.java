// ***************************************************************************
// Copyright (c) 2020, Industrial Logic, Inc., All Rights Reserved.
//
// This code is the exclusive property of Industrial Logic, Inc. It may ONLY be
// used by students during Industrial Logic's workshops or by individuals
// who are being coached by Industrial Logic on a project.
//
// This code may NOT be copied or used for any other purpose without the prior
// written consent of Industrial Logic, Inc.
// ****************************************************************************

package com.industriallogic.orders;

import com.industriallogic.util.Money;
import com.industriallogic.util.Weight;

public class Product {
	private Weight baseWeight;
	private Money basePrice;
	private String sku;
	private String description;

	public Product(String sku, String description, Money basePrice, Weight baseWeight) {
		this.sku = sku;
		this.description = description;
		this.basePrice = basePrice;
		this.baseWeight = baseWeight;
	}

	public String getSku() {
		return sku;
	}

	public String getDescription() {
		return description;
	}

	public Money getBasePrice() {
		return basePrice;
	}

	public Weight getBaseWeight() {
		return baseWeight;
	}
}
