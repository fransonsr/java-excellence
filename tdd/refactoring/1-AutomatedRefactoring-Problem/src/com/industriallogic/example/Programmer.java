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

package com.industriallogic.example;

import java.util.EnumSet;

public class Programmer extends Employee {
	private int jobsDone = 0;
	private int jobsSkipped = 0;

	public int jobsDoneCount() {
		return jobsDone;
	}
	
	public int jobsSkippedCount() {
		return jobsSkipped;
	}

	public void performJob(Job job) {
		EnumSet<Job> acceptableWork = EnumSet.of(Job.TEST, Job.PROGRAM, Job.INTEGRATE, Job.DESIGN);
		if (acceptableWork.contains(job))
			jobsDone++;
		else
			jobsSkipped++;
	}
}
