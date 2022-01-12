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

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.industriallogic.util.Money;

public class InvoiceTest {
	private ExchangeRateService previousService;

	@Before
	public void storePreviousService() {
		previousService = ExchangeRateService.getInstance();
	}

	@After
	public void restorePreviousService() {
		ExchangeRateService.setInstance(previousService);
	}

	private static class ExchangeRateServiceFake extends ExchangeRateService {
		private BigDecimal exchangeRate;

		ExchangeRateServiceFake(BigDecimal exchangeRate) {
			this.exchangeRate = exchangeRate;
		}

		public BigDecimal rateFor(String fromCurrency, String toCurrency) {
			return exchangeRate;
		}
	};

	@Test
	public void currencyConversion() {
		BigDecimal exchangeRate = new BigDecimal(1.2048);
		ExchangeRateService.setInstance(new ExchangeRateServiceFake(exchangeRate));
		Invoice invoice = new Invoice();
		Money netTotal = new Money(500);
		Money convertedTotal = invoice.convert(netTotal, "EUR");
		assertEquals(netTotal.times(exchangeRate), convertedTotal);
	}
}
