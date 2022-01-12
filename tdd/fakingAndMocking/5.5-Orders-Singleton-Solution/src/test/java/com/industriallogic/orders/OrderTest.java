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
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.industriallogic.util.Money;
import com.industriallogic.util.Weight;

public class OrderTest {
	private Order order;
	private Product largeProduct;
	private Product smallProduct;
	private LineItem oneLarge;
	private LineItem oneSmall;
	private LineItem threeSmall;

	@Before
	public void setUp() {
		largeProduct = new Product("SKU001", "Large Widget", new Money(123), new Weight(6));
		smallProduct = new Product("SKU002", "Small Widget", new Money(4.56), new Weight(0.10));
		oneLarge = new LineItem(largeProduct, 1);
		oneSmall = new LineItem(smallProduct, 1);
		threeSmall = new LineItem(smallProduct, 3);
		order = new TestableOrder("ACCOUNTID");
	}

	@Test
	public void orderId() {
		assertNotNull(order.getOrderId());
	}

	@Test
	public void empty() {
		assertEquals(Money.zero(), order.getNetTotal());
		assertEquals(Weight.zero(), order.getWeight());
	}

	@Test
	public void singleItemQuantityOne() {
		order.add(oneLarge);
		assertEquals(new Money(123), order.getNetTotal());
		assertEquals(new Weight(6), order.getWeight());
	}

	@Test
	public void singleItemQuantityThree() {
		order.add(threeSmall);
		assertEquals(new Money(13.68), order.getNetTotal());
		assertEquals(new Weight(0.3), order.getWeight());
	}

	@Test
	public void multipleItemsSameQuantity() {
		order.add(oneSmall);
		order.add(oneLarge);
		assertEquals(new Money(127.56), order.getNetTotal());
		assertEquals(new Weight(6.1), order.getWeight());
	}

	@Test
	public void multipleItemsDifferentQuantities() {
		order.add(threeSmall);
		order.add(oneLarge);
		assertEquals(new Money(136.68), order.getNetTotal());
		assertEquals(new Weight(6.3), order.getWeight());
	}
}
