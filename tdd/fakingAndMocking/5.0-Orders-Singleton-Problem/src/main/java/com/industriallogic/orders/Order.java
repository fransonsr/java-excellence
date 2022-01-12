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
import java.util.Vector;

import com.industriallogic.support.IdGenerator;
import com.industriallogic.support.InventoryControl;
import com.industriallogic.util.Money;
import com.industriallogic.util.Weight;

public class Order {
	public static final String DEFAULT_CURRENCY = "USD";
	private Money netTotal;
	private Weight weight;
	private Vector<LineItem> items;
	protected String accountId;
	private String orderId;
	private String currency;

	public Order(String accountId) {
		this(accountId, null);
	}

	public Order(String accountId, String orderId) {
		this.orderId = orderId;
		this.accountId = accountId;
		this.items = new Vector<LineItem>();
		this.netTotal = new Money(0);
		this.weight = new Weight(0);
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void add(LineItem item) {
		items.add(item);
		netTotal = netTotal.add(item.getExtendedAmount());
		weight = weight.add(item.getExtendedWeight());
		reserveProduct(item);
	}

	protected void reserveProduct(LineItem item) {
		InventoryControl.get().reserveProduct(getOrderId(),item.getSku(), item.getQuantity());
	}

	public Iterator<LineItem> getItems() {
		return items.iterator();
	}

	public String getCurrency() {
		return currency;
	}

	public Money getNetTotal() {
		return netTotal;
	}

	public Weight getWeight() {
		return weight;
	}

	public String getOrderId() {
		if (orderId == null)
			orderId = getOrderIdFromGenerator();
		return orderId;
	}

	protected String getOrderIdFromGenerator() {
		return IdGenerator.getOrderId(accountId);
	}
}
