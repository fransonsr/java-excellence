package com.industriallogic.junit.introduction;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class PassingTests {
	@Test
	public void green() {
		// pass by default when there's no code
	}

	@Test
	public void stringEquality() {
		String expected = "Something";
		String actual   = "Something";
		assertEquals(expected, actual);
	}

	@Test
	public void intEquality() {
		int expected = 10;
		int actual   = 10;
		assertEquals(expected, actual);
	}

	@Test
	public void integerEquality() {
		Integer expected = Integer.valueOf(11);
		Integer actual   = Integer.valueOf(11);
		assertEquals(expected, actual);
	}

	@Test
	public void doubleEquality() {
		double expected  = 10.01;
		double actual    = 10.01;
		double precision = 0.000001;
		assertEquals(expected, actual, precision);
	}

	@Test
	public void floatEquality() {
		float expected  = 10.0001F;
		float actual    = 10.00009F;
		float precision =  0.0001F;
		assertEquals("floats approximately equal", expected, actual, precision);
	}
	
	@Test
	public void objectsSame() {
		Integer anObject = Integer.valueOf(1);
		Integer sameObject = anObject;
		assertSame(anObject, sameObject);
	}

	@Test
	public void objectsNotSame() {
		Integer anObject = Integer.valueOf(1);
		Integer anotherObject = Integer.valueOf(1);
		assertNotSame(anObject, anotherObject);
	}

	@Test
	public void booleanTrue() {
		boolean result = true;
		assertTrue(result);
	}

	@Test
	public void booleanFalse() {
		boolean result = false;
		assertFalse(result);
	}
	
	@Test
	public void nonNullObject() {
		String object = "hello";
		assertNotNull(object);	
	}
	
	@Test
	public void nullObject() {
		String object = null;
		assertNull(object);	
	}

	@Test
	public void expectedException() {
		String stringToDecode = "a string";
		try {
			Integer.parseInt(stringToDecode);
			fail("Should have thrown NumberFormatException");
		}
		catch (NumberFormatException success) {
			// if we get here it means the test has passed
		}
	}
}
