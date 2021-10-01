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

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;

public class TailQueueTest {
	private TailQueue queue = new TailQueue();
	private Calendar now = Calendar.getInstance();

	@Test
	public void startsEmpty() {
		assertNull(queue.get());
	}

	@Test
	public void addOneTail() {
		add(1, Tail.IMPORTANT);
		assertGetEquals(1);
	}

	@Test(expected = TailAlreadyInQueueException.class)
	public void addThrowsOnExistingTail() {
		Tail tail = new Tail(1, Tail.URGENT, null);
		queue.add(tail);

		Tail secondTailWithSameId = new Tail(1, Tail.IMPORTANT, Calendar.getInstance());
		queue.add(secondTailWithSameId);
	}

	@Test
	public void sameQueueCases() {
		Calendar earlier = Calendar.getInstance();
		earlier.add(Calendar.SECOND, -1);
		Calendar later = Calendar.getInstance();
		later.add(Calendar.SECOND, 1);
		add(new Tail(4, Tail.URGENT, earlier));
		add(new Tail(3, Tail.URGENT, now));
		add(new Tail(2, Tail.URGENT, now));
		add(new Tail(1, Tail.URGENT, later));
		assertGetEquals(4);
		assertGetEquals(2);
		assertGetEquals(3);
		assertGetEquals(1);
	}

	@Test
	public void simpleOrdering() {
		add(1, Tail.DISMISSED);
		add(2, Tail.REQUESTING);
		add(3, Tail.IMPORTANT);
		add(4, Tail.URGENT);
		assertGetEquals(4);
		assertGetEquals(3);
		assertGetEquals(2);
		assertGetEquals(1);
	}

	@Test
	public void basicPreference() {
		add(1, Tail.REQUESTING);
		add(2, Tail.REQUESTING);
		add(3, Tail.IMPORTANT);
		add(4, Tail.IMPORTANT);
		add(5, Tail.IMPORTANT);
		assertGetEquals(3);
		assertGetEquals(1);
		assertGetEquals(4);
		assertGetEquals(5);
		assertGetEquals(2);
	}

	@Test
	public void urgentDoesntChangeSequence() {
		Tail urgent = new Tail(6, Tail.URGENT, Calendar.getInstance());
		add(1, Tail.REQUESTING);
		add(2, Tail.REQUESTING);
		add(3, Tail.IMPORTANT);
		add(4, Tail.IMPORTANT);
		add(5, Tail.IMPORTANT);
		addAndGet(urgent);
		assertGetEquals(3);
		addAndGet(urgent);
		assertGetEquals(1);
		addAndGet(urgent);
		assertGetEquals(4);
		addAndGet(urgent);
		assertGetEquals(5);
		addAndGet(urgent);
		assertGetEquals(2);
	}

	@Test
	public void dismissedDoesntChangeSequence() {
		add(1, Tail.DISMISSED);
		assertGetEquals(1);
		add(2, Tail.IMPORTANT);
		add(3, Tail.REQUESTING);
		assertGetEquals(2);		
	}

	@Test
	public void missingImportantDoesntChangeSequence() {
		add(1, Tail.REQUESTING);
		add(2, Tail.REQUESTING);
		assertGetEquals(1);
		add(3, Tail.IMPORTANT);
		assertGetEquals(3);
		assertGetEquals(2);
	}

        @Test
        public void missingRequestingDoesntChangeSequence() {
            add(1, Tail.IMPORTANT);
            add(2, Tail.IMPORTANT);
            add(4, Tail.IMPORTANT);
            assertGetEquals(1);
            assertGetEquals(2);
            add(3, Tail.REQUESTING);
            assertGetEquals(3);
            assertGetEquals(4);
        }

	private void addAndGet(Tail t) {
		add(t);
		assertEquals(t, queue.get());
	}

	private void assertGetEquals(int id) {
		Tail t = queue.get();
		assertEquals(t.id, id);
	}

	private void add(int id, int priority) {
		add(new Tail(id, priority, Calendar.getInstance()));
	}

	private void add(Tail t) {
		queue.add(t);
	}
}
