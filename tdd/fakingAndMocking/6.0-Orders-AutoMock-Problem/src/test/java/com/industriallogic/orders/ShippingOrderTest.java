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
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.industriallogic.support.Shipper;
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
	private FakeShipper shipper;

	static class FakeShipper extends Shipper {
		List<String> skus;
		List<Integer> quantities;
		List<Weight> weights;

		FakeShipper(String orderId, ShippingOption option) {
			super(orderId,option);
			skus = new ArrayList<String>();
			quantities = new ArrayList<Integer>();
			weights = new ArrayList<Weight>();
		}

		@Override
		public void addItem(String sku, int quantity, Weight weight) {
			skus.add(sku);
			quantities.add(new Integer(quantity));
			weights.add(weight);
		}

		@Override
		public Money total() {
			return new Money(skus.size());
		}
	}

	@Before
	public void setUp() {
		largeProduct = new Product("SKU001", "Large Widget", new Money(123), new Weight(6));
		smallProduct = new Product("SKU002", "Small Widget", new Money(4.56), new Weight(0.10));
		oneLarge = new LineItem(largeProduct, 1);
		oneSmall = new LineItem(smallProduct, 1);
		baseOrder = new TestableOrder("ACCOUNTID");
		shippingOrder = new ShippingOrder(baseOrder,ShippingOption.AIR);
		shipper = new FakeShipper(baseOrder.getOrderId(), ShippingOption.AIR);
	}

	@Test
	public void shippingAmountSingle() {
		baseOrder.add(oneLarge);
		shippingOrder.prepareToShip(shipper);
		assertEquals(new Money(1), shippingOrder.getShippingTotal());
		assertShipperItem(0, oneLarge);
	}

	@Test
	public void shippingAmountMultiple() {
		baseOrder.add(oneLarge);
		baseOrder.add(oneSmall);
		shippingOrder.prepareToShip(shipper);
		assertEquals(new Money(2), shippingOrder.getShippingTotal());
		assertShipperItem(0, oneLarge);
		assertShipperItem(1, oneSmall);
	}

	private void assertShipperItem(int item, LineItem lineItem) {
		assertShipperItem(item, lineItem.getSku(), lineItem.getQuantity(), lineItem.getExtendedWeight());
	}

	private void assertShipperItem(int item, String sku, int quantity, Weight weight) {
		assertTrue("Missing item " + item, item < shipper.skus.size());
		assertEquals("Wrong sku " + item, sku, shipper.skus.get(item));
		assertEquals("Wrong quantity " + item, quantity, shipper.quantities.get(item).intValue());
		assertEquals("Wrong weight " + item, weight, shipper.weights.get(item));
	}
}
