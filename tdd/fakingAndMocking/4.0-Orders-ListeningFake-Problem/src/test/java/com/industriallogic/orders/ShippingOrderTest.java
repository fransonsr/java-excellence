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

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.industriallogic.support.ShippingOption;
import com.industriallogic.util.Money;
import com.industriallogic.util.Weight;

public class ShippingOrderTest {
	private Order baseOrder;
	private ShippingOrder shippingOrder;
	private Product largeProduct;
	private Product smallProduct;
	private LineItem oneLarge;
	private LineItem oneSmall;
	private ShippingOption option;

	@Before
	public void setUp() {
		largeProduct = new Product("SKU001", "Large Widget", new Money(123), new Weight(6));
		smallProduct = new Product("SKU002", "Small Widget", new Money(4.56), new Weight(0.10));
		oneLarge = new LineItem(largeProduct, 1);
		oneSmall = new LineItem(smallProduct, 1);
		baseOrder = new Order("ACCOUNTID");
		option = ShippingOption.AIR;
		shippingOrder = new ShippingOrder(baseOrder, option);
	}

	@Test
	public void shippingAmountSingle() {
		baseOrder.add(oneLarge);
		shippingOrder.prepareToShip();
		assertEquals(option.rate(oneLarge.getExtendedWeight()), shippingOrder.getShippingTotal());
	}

	@Test
	public void shippingAmountMultiple() {
		baseOrder.add(oneLarge);
		baseOrder.add(oneSmall);
		shippingOrder.prepareToShip();
		Weight weight = oneLarge.getExtendedWeight().add(oneSmall.getExtendedWeight());
		assertEquals(option.rate(weight), shippingOrder.getShippingTotal());
	}
}
