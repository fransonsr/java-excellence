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

package com.industriallogic.dragons.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class DiceTest {

	@Test
	public void correctCounts() {
		assertEquals(0, new Dice(1).roll(0).length);
		assertEquals(1, new Dice(1).roll(1).length);
		assertEquals(2, new Dice(1).roll(2).length);
	}

	@Test
	public void correctSequence() {
		final int count = 10;
		int[] randoms = new int[10];
		final long seed = 13L;
		Random source = new Random(seed);
		for (int i = 0; i < count; i++)
			randoms[i] = source.nextInt(6) + 1;

		Dice dice = new Dice(seed);
		int[] rolls = dice.roll(count);
		assertArrayEquals(randoms, rolls);
	}
}
