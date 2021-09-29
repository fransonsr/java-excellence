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

public class Map {
	private static int INITIAL_CAPACITY = 10;
	protected Object[] keys = new Object[INITIAL_CAPACITY];
	protected Object[] values = new Object[INITIAL_CAPACITY];
	private int size = 0;
	private int indexWhereKeyFound;
	private boolean readOnly;

	public boolean isEmpty() {
		return size == 0;
	}

	public void add(Object key, Object value) {
		if (!readOnly) {
			for (int i = 0; i < size; i++)
				if (keys[i].equals(key)) {
					values[i] = value;
					return;
				}

			int newSize = size + 1;
			if (newSize > keys.length) {
				Object[] newKeys = new Object[keys.length + INITIAL_CAPACITY];
				Object[] newValues = new Object[keys.length + INITIAL_CAPACITY];
				System.arraycopy(keys, 0, newKeys, 0, size);
				System.arraycopy(values, 0, newValues, 0, size);
				keys = newKeys;
				values = newValues;
			}

			keys[size] = key;
			values[size] = value;
			size++;
		}
	}

	public int size() {
		return size;
	}

	public boolean remove(Object key) {
		if (readOnly)
			return false;
		for (int i = 0; i < size; i++)
			if (keys[i].equals(key)) {
				keys[i] = null;
				values[i] = null;
				size--;
				return true;
			}
		return false;
	}

	public boolean contains(Object value) {
		for (int i = 0; i < size; i++)
			if ((value == null && values[i] == null)
				|| (values[i] != null && values[i].equals(value)))
				return true;
		return false;
	}

	public boolean containsKey(Object key) {
		for (int i = 0; i < size; i++)
			if (keys[i] != null && keys[i].equals(key)) {
				indexWhereKeyFound = i;
				return true;
			}
		return false;
	}

	public Object get(Object key) {
		if (!containsKey(key))
			return null;
		return values[indexWhereKeyFound];
	}

	public int capacity() {
		return keys.length;
	}

	public void setReadOnly(boolean b) {
		readOnly = b;
	}

	public void addAll(Map m) {
		for (int i=0; i<m.size(); i++) 
			add(m.keys[i], m.values[i]);
	}
}
