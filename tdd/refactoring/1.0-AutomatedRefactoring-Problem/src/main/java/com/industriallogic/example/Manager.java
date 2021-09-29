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

public class Manager extends Employee {
	private int jobsCompleted = 0;
	private int jobsSkipped = 0;

	public int jobsCompletedCount() {
		return jobsCompleted;
	}
	
	public int jobsSkippedCount() {
		return jobsSkipped;
	}

	public void performJob(Job job) {
		if (responsibilities().contains(job))
			jobsCompleted++;
		else
			jobsSkipped++;
	}
	
	private EnumSet<Job> responsibilities() {
		return EnumSet.of(Job.MANAGE, Job.MARKET, Job.SELL);
	}
}
