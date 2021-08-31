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

public abstract class AbstractCollection {
	public void addAll(AbstractCollection c) {
		if (c instanceof Set) {
			Set s = (Set)c;
			for (int i=0; i < s.size(); i++) {
				if (!contains(s.getElementAt(i))) {
					add(s.getElementAt(i));
				}
			}
		} else if (c instanceof List) {
			List l = (List)c;
			for (int i=0; i < l.size(); i++) {
				if (!contains(l.get(i))) {
					add(l.get(i));
				}
			}
		} else if (c instanceof Map) {
			Map m = (Map)c;
			for (int i=0; i<m.size(); i++) 
				add(m.keys[i], m.values[i]);			
		}
	}
	
	public abstract boolean isEmpty();
	public abstract void add(Object element);
	public abstract boolean remove(Object element);
	public abstract boolean contains(Object element);
	public abstract int size();	
	
	public void add(Object key, Object value) {
	}
}
