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

import java.math.BigDecimal;

import com.industriallogic.util.Money;

public class Invoice {

	public void prepareInvoice() {
		// ...
	}

	public Money convert(Money amount, String toCurrency) {
		ExchangeRateService service = ExchangeRateService.getInstance();
		BigDecimal exchangeRate = service.rateFor(Order.DEFAULT_CURRENCY, toCurrency);
		return amount.times(exchangeRate); 
	}

}
