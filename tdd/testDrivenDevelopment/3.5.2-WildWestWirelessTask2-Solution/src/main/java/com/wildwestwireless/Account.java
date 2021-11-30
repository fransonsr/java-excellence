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
	private final int numberOfLines;
	private final double monthlyRate;
	private final double additionalLineRate;
	private int minutesUsed;
	private final int includedMinutes;
	private final double excessMinuteRate;

	public Account(int numberOfLines, double monthlyRate, double additionalLineRate, int includedMinutes, double excessMinuteRate) {
		this.numberOfLines = numberOfLines;
		this.monthlyRate = monthlyRate;
		this.additionalLineRate = additionalLineRate;
		this.includedMinutes = includedMinutes;
		this.excessMinuteRate = excessMinuteRate;
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
		return (numberOfLines - 1) * additionalLineRate;
	}

	public void setMinutesUsed(int minutesUsed) {
		this.minutesUsed = minutesUsed;
	}
}
