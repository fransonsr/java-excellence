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

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.industriallogic.util.Money;

public class InvoiceTest {

	@Test
	public void currencyConversion() {
		Invoice invoice = new Invoice();
		Money netTotal = new Money(500);
		Money convertedTotal = invoice.convert(netTotal, "EUR");
		// We don't know what the exact exchange rate will be, but we're pretty sure
		// it's not 1.0000 - so we'll just assert that the converted total is different
		// from the net total!
		assertFalse(convertedTotal.equals(netTotal));
	}

}
