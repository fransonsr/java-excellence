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

import org.junit.Before;
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
	private static final double GOLD_EXCESS_MINUTES_RATE = 0.45;
	private static final int GOLD_INCLUDED_MINUTES = 1000;
	private static final int SILVER_INCLUDED_MINUTES = 500;
	private static final double SILVER_EXCESS_MINUTES_RATE = 0.54;
	private Account goldAccountWithOneLine;
	private Account silverAccountWithOneLine;

	@Before
	public void setUp() {
		goldAccountWithOneLine = goldAccountWith(ONE_LINE);
		silverAccountWithOneLine = silverAccountWith(ONE_LINE);
	}

	@Test
	public void goldAccounts() {
		assertEquals(49.95, goldAccountWithOneLine.computeBill());
		assertEquals(64.45, goldAccountWith(TWO_LINES).computeBill());
	}

	@Test
	public void goldAccountWithTenMinutesAvailable() {
		goldAccountWithOneLine.setMinutesUsed(GOLD_INCLUDED_MINUTES - 10);
		assertEquals(GOLD_MONTHLY_RATE, goldAccountWithOneLine.computeBill());
	}

	@Test
	public void goldAccountWithTenExcessMinutes() {
		goldAccountWithOneLine.setMinutesUsed(GOLD_INCLUDED_MINUTES + 10);
		double expectedBill = GOLD_MONTHLY_RATE + (10 * GOLD_EXCESS_MINUTES_RATE);
		assertEquals(expectedBill, goldAccountWithOneLine.computeBill());
	}

	@Test
	public void silverAccountWithTenMinutesAvailable() {
		silverAccountWithOneLine.setMinutesUsed(SILVER_INCLUDED_MINUTES - 10);
		assertEquals(SILVER_MONTHLY_RATE, silverAccountWithOneLine.computeBill());
	}

	@Test
	public void silverAccountWithTenExcessMinutes() {
		silverAccountWithOneLine.setMinutesUsed(SILVER_INCLUDED_MINUTES + 10);
		double expectedBill = SILVER_MONTHLY_RATE + (10 * SILVER_EXCESS_MINUTES_RATE);
		assertEquals(expectedBill, silverAccountWithOneLine.computeBill());
	}

	@Test
	public void silverAccounts() {
		assertEquals(29.95, silverAccountWith(ONE_LINE).computeBill());
		assertEquals(72.95, silverAccountWith(THREE_LINES).computeBill());
	}	
	
	private Account silverAccountWith(int numberOfLines) {
		return new Account(
			numberOfLines, 
			SILVER_MONTHLY_RATE, 
			SILVER_ADDITIONAL_LINE_RATE, 
			SILVER_INCLUDED_MINUTES, 
			SILVER_EXCESS_MINUTES_RATE
		);
	}

	private Account goldAccountWith(int numberOfLines) {
		return new Account(
			numberOfLines, 
			GOLD_MONTHLY_RATE, 
			GOLD_ADDITIONAL_LINE_RATE, 
			GOLD_INCLUDED_MINUTES, 
			GOLD_EXCESS_MINUTES_RATE
		);
	}
}
