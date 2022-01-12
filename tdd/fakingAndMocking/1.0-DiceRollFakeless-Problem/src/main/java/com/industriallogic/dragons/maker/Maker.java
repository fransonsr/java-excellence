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

import com.industriallogic.dragons.util.Dice;

public class Maker {

	public Character character;

	public Maker() {
		this.character = new Character();
	}

	public void setStrengthFrom(Dice dice) {
		int sum = 0;
		int min = 6;
		for (int i = 0; i < 4; i++) {
			int roll = dice.roll();
			sum += roll;
			if (min > roll)
				min = roll;
		}
		character.strength = sum - min;
	}
}
