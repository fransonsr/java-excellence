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


public class Account {
	public static final double FAMILY_DISCOUNT = 5.0;
	private static final int MAX_STANDARD_LINES = 3;
	private int numberOfLines;
	private double monthlyRate;
	private double additionalLineRate;
	private int minutesUsed;
	private int includedMinutes;
	private double excessMinuteRate;

	private Account() {
	}

	public double computeBill() {
		return monthlyRate + excessMinutesRate() + additionalLineRate();
	}

	private double excessMinutesRate() {
		int excessMinutes = minutesUsed - includedMinutes;
		if (excessMinutes < 1)
			return 0.0;
		return (excessMinutes) * excessMinuteRate;
	}

	private double additionalLineRate() {
		return standardAdditionalLineRate() + familyAdditionalLineRate();
	}

	private double familyAdditionalLineRate() {
		if (numberOfLines > MAX_STANDARD_LINES)
			return (numberOfLines - MAX_STANDARD_LINES) * FAMILY_DISCOUNT;
		return 0.0;
	}

	private double standardAdditionalLineRate() {
		int standardLines = Math.min(numberOfLines, MAX_STANDARD_LINES);
		return (standardLines - 1) * additionalLineRate;
	}

	public void setMinutesUsed(int minutesUsed) {
		this.minutesUsed = minutesUsed;
	}

	public static Account goldAccountWith(int numberOfLines) {
		Account goldAccount = new Account();
		goldAccount.numberOfLines = numberOfLines;
		goldAccount.monthlyRate = 49.95;
		goldAccount.additionalLineRate = 14.50;
		goldAccount.includedMinutes = 1000;
		goldAccount.excessMinuteRate = 0.45;
		return goldAccount;
	}

	public static Account silverAccountWith(int numberOfLines) {
		Account silverAccount = new Account();
		silverAccount.numberOfLines = numberOfLines;
		silverAccount.monthlyRate = 29.95;
		silverAccount.additionalLineRate = 21.50;
		silverAccount.includedMinutes = 500;
		silverAccount.excessMinuteRate = 0.54;
		return silverAccount;		
	}
	
	double getExcessMinuteRate() {
		return excessMinuteRate;
	}
	
	int getIncludedMinutes() {
		return includedMinutes;
	}

	double getMonthlyRate() {
		return monthlyRate;
	}

	public double getAdditionalLineRate() {
		return additionalLineRate;
	}
}
