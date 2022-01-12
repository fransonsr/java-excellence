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

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.industriallogic.dragons.util.Dice;

public class MakerTest {

	private Maker maker;

	@Before
	public void before() {
		maker = new Maker();
	}

	@Test
	public void randomTestToGetBallRolling() {
		// predict result based on fixed seed
		final int count = 4;
		List<Integer> randomRolls = new LinkedList<Integer>();
		final long seed = 13L;
		Random source = new Random(seed);
		for (int i = 0; i < count; i++)
			randomRolls.add(0, source.nextInt(6) + 1);
		java.util.Collections.sort(randomRolls);
		int sumOfBestThree = randomRolls.get(1) + randomRolls.get(2)
				+ randomRolls.get(3);

		// use fixed seed in fresh run
		Maker maker = new Maker();
		maker.setStrengthFrom(new Dice(seed));
		assertEquals(sumOfBestThree, maker.character.strength);
	}

	@Test
	public void fourOnesIsThree() {
		assertStrengthEquals(3, 1, 1, 1, 1);
	}

	@Test
	public void fourSixesIsEighteen() {
		assertStrengthEquals(18, 6, 6, 6, 6);
	}

	@Test
	public void ignoresLowestRoll() {
		assertStrengthEquals(18, 1, 6, 6, 6);
		assertStrengthEquals(18, 2, 6, 6, 6);
		assertStrengthEquals(18, 3, 6, 6, 6);
		assertStrengthEquals(18, 4, 6, 6, 6);
		assertStrengthEquals(18, 5, 6, 6, 6);
	}

	@Test
	public void positionOfMinDoesNotMatter() {
		assertStrengthEquals(18, 1, 6, 6, 6);
		assertStrengthEquals(18, 6, 1, 6, 6);
		assertStrengthEquals(18, 6, 6, 1, 6);
		assertStrengthEquals(18, 6, 6, 6, 1);
	}

	public void assertStrengthEquals(int expected, int... rolls) {
		
		assertEquals(expected, maker.best3of4(rolls));
	}

}
