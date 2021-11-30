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

	public Account(int numberOfLines, double monthlyRate, double additionalLineRate) {
		this.numberOfLines = numberOfLines;
		this.monthlyRate = monthlyRate;
		this.additionalLineRate = additionalLineRate;
	}

	public double computeBill() {
		return monthlyRate + (numberOfLines - 1) * additionalLineRate;
	}
}
