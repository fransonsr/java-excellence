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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EmployeeTest {

	@Test
	public void noJobsPerformed() {
		Manager deadBeat = new Manager();
		assertEquals(0, deadBeat.jobsCompletedCount());
		assertEquals(0, deadBeat.jobsSkippedCount());
	}
	
	@Test
	public void threeJobsPerformedNoneSkippedByManager() {
		Manager productiveManager = new Manager();
		productiveManager.performJob(Job.MANAGE);
		productiveManager.performJob(Job.MARKET);
		productiveManager.performJob(Job.SELL);
		assertEquals(3, productiveManager.jobsCompletedCount());
		assertEquals(0, productiveManager.jobsSkippedCount());
	}
	
	@Test
	public void oneJobDoneOneJobSkippedByManager() {
		Manager unbusyManager = new Manager();
		unbusyManager.performJob(Job.TEST);
		unbusyManager.performJob(Job.SELL);
		assertEquals(1, unbusyManager.jobsCompletedCount());
		assertEquals(1, unbusyManager.jobsSkippedCount());
	}
	
	@Test
	public void noJobsDone() {
		Programmer deadBeat = new Programmer();
		assertEquals(0, deadBeat.jobsCompletedCount());
		assertEquals(0, deadBeat.jobsSkippedCount());
	}
	
	@Test
	public void threeJobsPerformedNoneSkippedByProgrammer() {
		Programmer productiveProgrammer = new Programmer();
		productiveProgrammer.performJob(Job.TEST);
		productiveProgrammer.performJob(Job.PROGRAM);
		productiveProgrammer.performJob(Job.INTEGRATE);
		assertEquals(3, productiveProgrammer.jobsCompletedCount());
		assertEquals(0, productiveProgrammer.jobsSkippedCount());
	}
	
	@Test
	public void oneJobDoneOneJobSkippedByProgrammer() {
		Programmer unbusyProgrammer = new Programmer();
		unbusyProgrammer.performJob(Job.TEST);
		unbusyProgrammer.performJob(Job.SELL);
		assertEquals(1, unbusyProgrammer.jobsCompletedCount());
		assertEquals(1, unbusyProgrammer.jobsSkippedCount());
	}	
}
