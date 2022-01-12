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

public class LineItem {
	private int quantity;
	private Product product;

	public LineItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getText() {
		return product.getSku() + ": " + product.getDescription();
	}

	public Money getItemAmount() {
		return product.getBasePrice();
	}

	public Money getExtendedAmount() {
		return new Money(quantity).times(product.getBasePrice());
	}

	public Weight getExtendedWeight() {
		return new Weight(quantity).times(product.getBaseWeight());
	}

	public String getSku() {
		return product.getSku();
	}
}
