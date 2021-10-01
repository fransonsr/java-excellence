package com.industriallogic.junit.introduction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class FailingTests {
	@Test
	public void stringEquality() {
		String expected = "Something";
		String actual   = "Something Else";
		assertEquals(expected, actual);
	}

	@Test
	public void intEquality() {
		int expected = 10;
		int actual   = 11;
		assertEquals(expected, actual);
	}

	@Test
	public void integerEquality() {
		Integer expected = Integer.valueOf(10);
		Integer actual   = Integer.valueOf(11);
		assertEquals(expected, actual);
	}

	@Test
	public void doubleEquality() {
		double expected  = 10.0;
		double actual    = 10.01;
		double precision = 0.000001;
 		assertEquals(expected, actual, precision);
	}

	@Test
	public void floatEquality() {
		float expected  = 10.0001F;
		float actual    = 10.0003F;
		float precision =  0.00001F;
		assertEquals("floats approximately equal", expected, actual, precision);
	}

	@Test
	public void objectsNotSame() {
		Integer anObject = Integer.valueOf(1);
		Integer sameObject = anObject;
		assertNotSame(anObject, sameObject);
	}
	
	@Test
	public void objectsSame() {
		Integer anObject = Integer.valueOf(1);
		Integer anotherObject = Integer.valueOf(1);
		assertSame(anObject, anotherObject);
	}
	
	@Test
	public void booleanTrue() {
		boolean result = false;
		assertTrue("result should have been true", result);
	}

	@Test
	public void booleanFalse() {
		boolean result = true;
		assertFalse("result should have been false", result);
	}
	
	@Test
	public void nullObjects() {
		Object object = null;
		assertNotNull(object);	
	}

	@Test
	public void nonNullObjects() {
		String object = "hello";
		assertNull(object);	
	}

	@Test
	public void failTheTest() {
		int x = 10;
		int y = 11;
		if (x != y) 
			fail("the two integers " + x + " and " + y + " did not match.");
	}
	
	@Test
	public void failAfterFirstAssert() {
		assertEquals(5, 20 / 5);  // should fail here 
		assertEquals("20 divided by 5 (never gets here)", 4, 20 / 5);
	}
}
