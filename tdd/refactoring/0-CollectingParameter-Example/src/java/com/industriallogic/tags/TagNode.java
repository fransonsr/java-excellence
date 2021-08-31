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


package com.industriallogic.tags;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TagNode {
	private String tagName = "";
	private String tagValue = "";
	private String attributes = "";
	private List children;
	
	public TagNode(String name) {
		this.tagName = name;
	}
	
	public void addAttribute(String name, String value) {
		attributes += (" " + name + "='" + value + "'"); 
	}

	public void addValue(String value) {
		this.tagValue = value;
	}
	
	public String toString() {
		String result;
		result = "<" + tagName + attributes + ">";
		Iterator it = children().iterator();
		while (it.hasNext()) {
			TagNode node = (TagNode)it.next();
			result += node.toString();
		}	
		if (!tagValue.equals(""))
			result += tagValue;
		result += "</" + tagName + ">";
		return result;
	}

	private List children() {
		if (children == null)
			children = new ArrayList();
		return children;	
	}
	
	public void add(TagNode child) {
		children().add(child);
	}
}
