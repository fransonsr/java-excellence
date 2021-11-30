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
	private Account goldAccountWithOneLine;
	private Account silverAccountWithOneLine;
	private static final int ONE_LINE = 1;
	private static final int TWO_LINES = 2;
	private static final int THREE_LINES = 3;
	private static final int FOUR_LINES = 4;
	private static final int FIVE_LINES = 5;
	private static final double PENNY_PRECISION = 0.01;

	@Before
	public void setUp() {
		goldAccountWithOneLine = Account.goldAccountWith(ONE_LINE);
		silverAccountWithOneLine = Account.silverAccountWith(ONE_LINE);
	}

	@Test
	public void goldAccounts() {
		assertBillEquals(49.95, goldAccountWithOneLine.computeBill());
		assertBillEquals(64.45, Account.goldAccountWith(TWO_LINES).computeBill());
	}

	@Test
	public void goldAccountWithTenMinutesAvailable() {
		goldAccountWithOneLine.setMinutesUsed(
			includedMinutesFor(goldAccountWithOneLine) - 10
		);
		assertBillEquals(
			rateFor(goldAccountWithOneLine), 
			goldAccountWithOneLine.computeBill()
		);
	}

	@Test
	public void goldAccountWithTenExcessMinutes() {
		goldAccountWithOneLine.setMinutesUsed(
			includedMinutesFor(goldAccountWithOneLine) + 10
		);
		double expectedBill = 
			rateFor(goldAccountWithOneLine) + 
			(10 * excessMinutesFor(goldAccountWithOneLine));
		assertBillEquals(expectedBill, goldAccountWithOneLine.computeBill());
	}

	@Test
	public void silverAccountWithTenMinutesAvailable() {
		silverAccountWithOneLine.setMinutesUsed(
			includedMinutesFor(silverAccountWithOneLine) - 10
		);
		assertBillEquals(
			rateFor(silverAccountWithOneLine), 
			silverAccountWithOneLine.computeBill()
		);
	}

	@Test
	public void silverAccountWithTenExcessMinutes() {
		silverAccountWithOneLine.setMinutesUsed(
			includedMinutesFor(silverAccountWithOneLine) + 10
		);
		double expectedBill = 
			rateFor(silverAccountWithOneLine) + 
			(10 * excessMinutesFor(silverAccountWithOneLine));
		assertBillEquals(expectedBill, silverAccountWithOneLine.computeBill());
	}

	@Test
	public void silverAccounts() {
		assertBillEquals(29.95, silverAccountWithOneLine.computeBill());
		assertBillEquals(72.95, Account.silverAccountWith(THREE_LINES).computeBill());
	}

	@Test
	public void goldFamilyDiscount() {
		Account familyAccount = Account.goldAccountWith(FOUR_LINES);
		
		double familyBill = 
			familyAccount.getMonthlyRate() +
			(2 * familyAccount.getAdditionalLineRate()) +
			Account.FAMILY_DISCOUNT;

		assertBillEquals(familyBill, familyAccount.computeBill());
	}

	@Test
	public void silverFamilyDiscount() {
		Account familyAccount = Account.silverAccountWith(FIVE_LINES);
		
		double familyBill = 
			familyAccount.getMonthlyRate() +
			(2 * familyAccount.getAdditionalLineRate()) +
			(2 * Account.FAMILY_DISCOUNT);

		assertBillEquals(familyBill, familyAccount.computeBill());
	}

	@Test
	public void variousAccounts() {
		assertBillEquals(105.3, billFor(Account.goldAccountWith(ONE_LINE), 1123));
		assertBillEquals(139.3, billFor(Account.goldAccountWith(FOUR_LINES), 1123));
		
		assertBillEquals(82.95, billFor(Account.silverAccountWith(FIVE_LINES), 44));
		assertBillEquals(94.29, billFor(Account.silverAccountWith(FIVE_LINES), 521));
	}
	
	private void assertBillEquals(double expectedBill, double actualBill) {
		assertEquals(expectedBill, actualBill, PENNY_PRECISION);
	}

	private double billFor(Account account, int minutesUsed) {
		account.setMinutesUsed(minutesUsed);
		return account.computeBill();
	}

	private int includedMinutesFor(Account account) {
		return account.getIncludedMinutes();
	}

	private double rateFor(Account account) {
		return account.getMonthlyRate();
	}

	private double excessMinutesFor(Account account) {
		return account.getExcessMinuteRate();
	}
}
