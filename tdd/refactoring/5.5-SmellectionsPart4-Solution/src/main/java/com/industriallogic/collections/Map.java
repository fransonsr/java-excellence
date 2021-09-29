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
	private List keys = new List(); 
	private List values = new List();
	private boolean readOnly;

	public boolean isEmpty() {
		return keys.isEmpty();
	}

	public void add(Object key, Object value) {
		if (readOnly)
			return;
		
		int indexOfKey = find(key);
		if (indexOfKey != -1) 
			values.set(indexOfKey, value);
		else {
			keys.add(key);
			values.add(value);
		}
	}

	public int size() {
		return keys.size();
	}

	public boolean remove(Object key) {
		if (readOnly)
			return false;
		
		int indexOfKey = find(key);
		if (indexOfKey == -1)
			return false;
		
		keys.removeElementAt(indexOfKey);
		values.removeElementAt(indexOfKey);
		return true;
	}

	public boolean contains(Object value) {
		for (int i = 0; i < values.size(); i++)
			if ((value == null && values.get(i) == null)
				|| (values.get(i) != null && values.get(i).equals(value)))
				return true;
		return false;
	}

	private int find(Object key) {
		for (int i = 0; i < keys.size(); i++)
			if (keys.get(i) != null && keys.get(i).equals(key))
				return i;		
		return -1;
	}
	
	public boolean containsKey(Object key) {
		return find(key) != -1;
	}

	public Object get(Object key) {
		int indexOfKey = find(key);
		if (indexOfKey == -1)
			return null;
		return values.get(indexOfKey);
	}

	public int capacity() {
		return keys.capacity();
	}

	public void setReadOnly(boolean b) {
		readOnly = b;
	}

	public void addAll(Map m) {
		for (int i=0; i < m.size(); i++) 
			add(m.keys.get(i), m.values.get(i));
	}
}
