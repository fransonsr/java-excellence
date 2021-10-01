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

package com.industriallogic.junit.introduction;

import org.junit.Test;

import com.industriallogic.junit.extensions.ExtendedTestCase;

public class UsingCustomAssert extends ExtendedTestCase {
	@Test
	public void strings() {
		String expected =
			"Yankee doodle went to town, riding on a pony.\n" +
			"He stuck a feather in his cap and called it macaroni.";
		String actual =
			"Yankee doodle went to town, riding on a pony\n" +
			"He stuck a feather in his cap and called it macaroni.";

		assertStringEquals("string matching", expected, actual);
	}
}
