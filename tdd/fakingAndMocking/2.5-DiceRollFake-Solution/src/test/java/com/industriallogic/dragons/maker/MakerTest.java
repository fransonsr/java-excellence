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

package com.industriallogic.dragons.maker;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.industriallogic.dragons.util.FakeDice;

public class MakerTest {

	private Maker maker;

	@Before
	public void before() {
		maker = new Maker();
	}

	@Test
	public void fourOnesIsThree() {
		assertStrengthFrom(3, 1, 1, 1, 1);
	}

	@Test
	public void fourSixesIsEighteen() {
		assertStrengthFrom(18, 6, 6, 6, 6);
	}

	@Test
	public void ignoresLowestRoll() {
		assertStrengthFrom(18, 1, 6, 6, 6);
		assertStrengthFrom(18, 2, 6, 6, 6);
		assertStrengthFrom(18, 3, 6, 6, 6);
		assertStrengthFrom(18, 4, 6, 6, 6);
		assertStrengthFrom(18, 5, 6, 6, 6);
	}

	@Test
	public void positionOfMinDoesNotMatter() {
		assertStrengthFrom(18, 1, 6, 6, 6);
		assertStrengthFrom(18, 6, 1, 6, 6);
		assertStrengthFrom(18, 6, 6, 1, 6);
		assertStrengthFrom(18, 6, 6, 6, 1);
	}

	private void assertStrengthFrom(int expected, int... rolls) {
		maker.setStrengthFrom(new FakeDice(rolls));
		assertEquals(expected, maker.character.strength);
	}

}
