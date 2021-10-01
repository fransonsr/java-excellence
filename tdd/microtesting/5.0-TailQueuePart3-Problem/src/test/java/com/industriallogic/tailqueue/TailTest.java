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

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class TailTest {
    private Calendar now;

    private Tail idTenUrgent;
    private Tail secondIdTenUrgent;
    private Tail idNineImportant;
    private Tail idEightRequesting;
    private Tail idSevenDismissed;

    @Before
    public void setUp() {
        now = Calendar.getInstance();

        idTenUrgent = nowTail(10, Tail.URGENT);
        secondIdTenUrgent = nowTail(10, Tail.URGENT);
        idNineImportant = nowTail(9, Tail.IMPORTANT);
        idEightRequesting = nowTail(8, Tail.REQUESTING);
        idSevenDismissed = nowTail(7, Tail.DISMISSED);
    }

    private Tail nowTail(int id, int priority) {
        return newTail(id, priority, now);
    }

    private Tail newTail(int id, int priority, Calendar time) {
        return new Tail(id, priority, time);
    }

    @Test
    public void equalsSelf() {
        assertEquals("equals self", 0, idTenUrgent.compareTo(idTenUrgent));
    }

    @Test
    public void sameID() {
        equals(idTenUrgent, secondIdTenUrgent);
    }

    @Test
    public void sameIDDifferentPriorityAndTime() {
        Calendar later = Calendar.getInstance();
        later.add(Calendar.SECOND, 20);
        Tail idTenRequesting = newTail(10, Tail.REQUESTING, later);
        equals(idTenUrgent, idTenRequesting);
    }

    @Test
    public void priorityDifferences() {
        lessThan(idTenUrgent, idNineImportant);
        lessThan(idNineImportant, idEightRequesting);
        lessThan(idEightRequesting, idSevenDismissed);
    }

    @Test
    public void timeDifferences() {
        Calendar earlier = Calendar.getInstance();
        earlier.add(Calendar.SECOND, -1);
        Calendar later = Calendar.getInstance();
        later.add(Calendar.SECOND, 1);

        Tail idSixEarlier = newTail(6, Tail.URGENT, earlier);
        Tail idFiveLater = newTail(5, Tail.URGENT, later);

        lessThan(idSixEarlier, idFiveLater);
    }

    @Test
    public void idOnlyDifferences() {
        Tail idSixDismissed = nowTail(6, Tail.DISMISSED);
        lessThan(idSixDismissed, idSevenDismissed);
    }

    private void equals(Tail one, Tail another) {
        assertEquals("equals forward", 0, one.compareTo(another));
        assertEquals("equals backward", 0, another.compareTo(one));
    }

    private void lessThan(Tail lesser, Tail greater) {
        assertEquals("first argument lesser", -1, lesser.compareTo(greater));
        assertEquals("second argument greater", 1, greater.compareTo(lesser));
    }
}
