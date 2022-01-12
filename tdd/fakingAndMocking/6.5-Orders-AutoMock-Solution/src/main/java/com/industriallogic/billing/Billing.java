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

package com.industriallogic.billing;

import com.industriallogic.util.Money;

public class Billing {

	public final static String MERCHANT_ID = "Widgets.com";
	private final CactusClient cactusClient;

	public Billing() {
		this(new CactusClientImpl());
	}
	
	public Billing(CactusClient cactusClient) {
		this.cactusClient = cactusClient;
	}

	public boolean bill(HolderInfo holderInfo, TransactionInfo[] transactions) {
		boolean success = false;

		if (cactusClient.startSession(MERCHANT_ID)) {
			cactusClient.setHolderInfo(holderInfo);
			if (cactusClient.hasSufficientFundsFor(totalAmountFor(transactions))) {
				for (int transaction = 0; transaction < transactions.length; transaction++)
					cactusClient.process(transactions[transaction]);
				success = true;
			}
			cactusClient.stopSession();
		}

		return success;
	}

	private Money totalAmountFor(TransactionInfo[] transactions) {
		Money total = new Money(0);
		for (TransactionInfo transaction : transactions)
			total = total.add(transaction.amount);
		return total;
	}
}
