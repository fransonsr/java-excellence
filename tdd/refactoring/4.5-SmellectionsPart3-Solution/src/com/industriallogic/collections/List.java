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

package com.industriallogic.collections;

public class List extends AbstractCollection {
	public void set(int i, Object value) {
		if (readOnly)
			return;
			
		if (i >= size)
			throw new ArrayIndexOutOfBoundsException();

		elements[i] = value;
	}
}
