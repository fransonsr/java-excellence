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

package com.wildwestwireless;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class AccountTest {
	private static final double GOLD_ADDITIONAL_LINE_RATE = 14.50;
	private static final double GOLD_MONTHLY_RATE = 49.95;
	private static final int ONE_LINE = 1;
	private static final int TWO_LINES = 2;
	private static final int THREE_LINES = 3;
	private static final double SILVER_MONTHLY_RATE = 29.95;
	private static final double SILVER_ADDITIONAL_LINE_RATE = 21.50;

	@Test
	public void goldAccounts() {
		assertEquals(49.95, goldAccountWith(ONE_LINE).computeBill());
		assertEquals(64.45, goldAccountWith(TWO_LINES).computeBill());
	}

	@Test
	public void silverAccounts() {
		assertEquals(29.95, silverAccountWith(ONE_LINE).computeBill());
		assertEquals(72.95, silverAccountWith(THREE_LINES).computeBill());
	}	
	
	private Account silverAccountWith(int numberOfLines) {
		return new Account(numberOfLines, SILVER_MONTHLY_RATE, SILVER_ADDITIONAL_LINE_RATE);
	}

	private Account goldAccountWith(int numberOfLines) {
		return new Account(numberOfLines, GOLD_MONTHLY_RATE, GOLD_ADDITIONAL_LINE_RATE);
	}
}
