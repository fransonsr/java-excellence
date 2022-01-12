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

import java.util.Random;

public class Dice {
	Random random;

	public Dice(long seed) {
		this.random = new Random(seed);
	}

	public int[] roll(int count) {
		int[] result = new int[count];
		for (int i = 0; i < count; i++)
			result[i] = roll();
		return result;
	}

	public int roll() {
		return random.nextInt(6) + 1;
	}
}
