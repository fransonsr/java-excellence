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

package com.industriallogic.examples;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

public class XRayTest {

	private static final Object[] EMPTY = {};

	@Test
	public void testPatientExceptionCatcher() {
		try {
			testPatient();
		} catch (Exception e) {
			fail();
		}
	}

	public void testPatient() throws Exception {
		// Use reflection to evaluate: assertTrue(patient.getGland().enlarged);
		Patient patient = new Patient();
		Class patientClass = patient.getClass();
		Method getGlandMethod = patientClass.getDeclaredMethod("getGland");
		getGlandMethod.setAccessible(true);
		Object gland = getGlandMethod.invoke(patient, EMPTY);
		Field glandField = gland.getClass().getDeclaredField("enlarged");
		glandField.setAccessible(true);
		boolean enlarged = (Boolean) glandField.get(gland);
		assertTrue(enlarged);
	}
}
