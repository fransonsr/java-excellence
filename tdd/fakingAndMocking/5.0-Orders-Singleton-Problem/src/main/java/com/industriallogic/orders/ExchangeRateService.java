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
import java.util.HashMap;
import java.util.Map;

import com.industriallogic.support.WebServiceGateway;

public class ExchangeRateService {
	private static ExchangeRateService instance = new ExchangeRateService();

	private ExchangeRateService() {}

	public static ExchangeRateService getInstance() {
		return instance;
	}

	public BigDecimal rateFor(String fromCurrency, String toCurrency) {
		WebServiceGateway service = new WebServiceGateway();
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("provider", "TheFirstBankOfMicrotesting.com/TURTLE");
		parameters.put("service", "CurrentExchangeRate");
		parameters.put("from", fromCurrency);
		parameters.put("to", toCurrency);
		return (BigDecimal)service.execute(parameters);
	}
}
