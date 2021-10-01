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

package com.industriallogic.soundCheck;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SoundCheckTest {
	@Test
	public void weCanTest() {
		assertEquals("it works", new SoundCheck().doit());
	}
}
