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

import java.util.Calendar;

public class Tail implements Comparable {
	public static final int URGENT = 0;
	public static final int IMPORTANT = 1;
	public static final int REQUESTING = 2;
	public static final int DISMISSED = 3;
	
	int id;
	int priorityClass;
	Calendar lastContactTime;

	public Tail(int id, int priorityClass, Calendar lastContactTime) {
		this.id = id;
		this.priorityClass = priorityClass;
		this.lastContactTime = lastContactTime;
	}

	public int compareTo(Object arg0) {
		Tail other = (Tail) arg0;
		if (id == other.id) return 0;
		if (priorityClass < other.priorityClass) return -1;
		if (priorityClass > other.priorityClass) return 1;
		if (lastContactTime.before(other.lastContactTime)) return -1;
		if (lastContactTime.after(other.lastContactTime)) return 1;
		if (id < other.id) return -1;
		return 1;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Tail{");
		sb.append("id=")
		  .append(id);
		sb.append(", priorityClass=")
		  .append(priorityClass);
		sb.append('}');
		return sb.toString();
	}
}
