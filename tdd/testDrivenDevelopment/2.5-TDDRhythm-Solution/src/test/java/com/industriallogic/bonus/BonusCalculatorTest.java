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

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BonusCalculatorTest {
	private static final double precision = 0.001;
	private BonusCalculator bonusCalculator;  

	@Before
	public void setUp() {
		bonusCalculator = new BonusCalculator();
	}
	
	@Test
	public void individualBonusWhenSalesAboveQuota() {
		assertEquals(90.0, bonusCalculator.individualBonus(12000, 11000, 10.0, 10.0), precision);
	}
	
	@Test
	public void individualBonusWhenSalesBelowQuota() {
		assertEquals(0.0, bonusCalculator.individualBonus(12000, 15000, 10.0, 10.0), precision);
	}
	
	@Test
	public void individualBonusWhenSalesEqualQuota() {
		assertEquals(0.0, bonusCalculator.individualBonus(12000, 12000, 10.0, 10.0), precision);
	}
	
	@Test
	public void teamBonusWhenSalesAboveQuota() {
		assertEquals(25.0, bonusCalculator.teamBonus(12000, 11000, 10.0, 4), precision);
	}
	
	@Test
	public void teamBonusWhenSalesBelowQuota() {
		assertEquals(0.0, bonusCalculator.teamBonus(12000, 15000, 10.0, 4), precision);
	}
	
	@Test
	public void teamBonusWhenSalesEqualQuota() {
		assertEquals(0.0, bonusCalculator.teamBonus(12000, 12000, 10.0, 4), precision);
	}
	
	@Test
	public void teamBonusWhenNoTeamMembersExist() {
		assertEquals(0.0, bonusCalculator.teamBonus(12000, 11000, 10.0, 0), precision);
	}
}
