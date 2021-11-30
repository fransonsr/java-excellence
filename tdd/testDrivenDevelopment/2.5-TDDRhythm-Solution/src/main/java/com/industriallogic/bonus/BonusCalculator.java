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

package com.industriallogic.bonus;

public class BonusCalculator {

	public double individualBonus(int sales, int quota, double commission, double tax) {
		return baseBonus(sales, quota, commission) * taxAdjustmentFactor(tax);
	}

	private double taxAdjustmentFactor(double tax) {
		return 1.0 - tax / 100.0;
	}

	public double teamBonus(int sales, int quota, double commission, int numberOfTeamMembers) {
		if (numberOfTeamMembers == 0)
			return 0.0;
		
		return baseBonus(sales, quota, commission) / numberOfTeamMembers ;
	}

	private double baseBonus(int sales, int quota, double commission) {
		return  (sales - quota) > 0 ? (sales - quota) * commission / 100.0 : 0.0;
	}
}
