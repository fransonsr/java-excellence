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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSetupAndTearDown {
	private List<String> list;

	@Before
	public void createList() {
		list = new ArrayList<String>();
	}

	@After
	public void clearList() {
		list.clear();
	}

	@Test
	public void emptyCollection() {
		assertTrue(list.isEmpty());
	}

	@Test
	public void twoItemCollection() {
		list.add("cat");
		list.add("mouse");
		assertEquals(2, list.size());
	}
}
