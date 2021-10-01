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

package com.industriallogic.junit.extensions;

import org.junit.Assert;

public abstract class ExtendedTestCase {

	protected void assertStringEquals(String message, String expected, String actual) {
		String mismatchInfo = "";

		if (expected.length() < actual.length()) {
			mismatchInfo = "\n\nACTUAL result has "+(actual.length()-expected.length())+" extra characters at the end. They are :";

			for (int i = expected.length(); i < actual.length(); i++) {
				mismatchInfo += ("\nPosition : " + i + " , Code = " + (int) actual.charAt(i));
			}
		} else if(expected.length() > actual.length()) {
			mismatchInfo = "\n\nEXPECTED result has "+(expected.length()-actual.length())+" extra characters at the end. They are :";

			for (int i = actual.length(); i < expected.length(); i++) {
				mismatchInfo += ("\nPosition : " + i + " , Code = " + (int) expected.charAt(i));
			}
		}
		for (int i = 0; i < expected.length(); i++) {
			if (
					(expected.length() != actual.length() &&
							(
									i >= (expected.length()-1 ) ||
											i >= (actual.length()-1 )
							)
					) ||
							(actual.charAt(i) != expected.charAt(i))
			) {
				StringBuffer errorMsg = new StringBuffer();
				errorMsg.append(
						message + mismatchInfo + " \nMismatch of strings at char posn " + i +
								" \n\nString Expected upto mismatch = " + expected.substring(0, i) +
								" \n\nString Actual upto mismatch = " + actual.substring(0, i)
				);
				errorMsg.append(
						" \n\nString Expected MISMATCH CHARACTER = "+ expected.charAt(i) +
								", code = " + (int) expected.charAt(i)
				);

				if (i < actual.length())
					errorMsg.append(
							" \n\nString Actual MISMATCH CHARACTER = " + actual.charAt(i) +
									", code = " + (int) actual.charAt(i)
					);

				errorMsg.append(
						" \n\n**** COMPLETE STRING EXPECTED ****\n" + expected +
								" \n\n**** COMPLETE STRING ACTUAL***\n" + actual
				);
				Assert.fail(errorMsg.toString());
			}
		}
	}
}
