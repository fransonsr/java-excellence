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

package com.industriallogic.tailqueue;

import static org.junit.Assert.assertSame;

import java.util.Calendar;

import org.junit.Test;

public class TailQueueTest {
	private TailQueue queue = new TailQueue();

	@Test
	public void addOneTail() {
		Tail tail = new Tail(1, Tail.IMPORTANT, null);
		queue.add(tail);
		assertSame(tail, queue.get());
	}

	@Test(expected = TailAlreadyInQueueException.class)
	public void addThrowsOnExistingTail() {
		Tail tail = new Tail(1, Tail.URGENT, null);
		queue.add(tail);

		Tail secondTailWithSameId = new Tail(1, Tail.IMPORTANT, Calendar.getInstance());
		queue.add(secondTailWithSameId);
	}
}
