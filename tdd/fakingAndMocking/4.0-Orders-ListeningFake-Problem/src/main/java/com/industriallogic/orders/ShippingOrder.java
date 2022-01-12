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

import java.util.Iterator;

import com.industriallogic.support.Shipper;
import com.industriallogic.support.ShippingOption;
import com.industriallogic.util.Money;

public class ShippingOrder {
	private Order baseOrder;
	private ShippingOption option;
	private Money shippingTotal;
	
	public ShippingOrder(Order baseOrder, ShippingOption option) {
		this.baseOrder = baseOrder;
		this.option = option;
		this.shippingTotal = Money.zero();
	}
	
	public void prepareToShip() {
		Shipper shipper = new Shipper(getBaseOrder().getOrderId(), option);
		Iterator<LineItem> items = getBaseOrder().getItems();
		while (items.hasNext()) {
			LineItem item = items.next();
			shipper.addItem(item.getSku(), item.getQuantity(), item.getExtendedWeight());
		}
		shippingTotal = shipper.total();
	}
	
	public Money getShippingTotal() {
		return shippingTotal;
	}

	public Order getBaseOrder() {
		return baseOrder;
	}
}
