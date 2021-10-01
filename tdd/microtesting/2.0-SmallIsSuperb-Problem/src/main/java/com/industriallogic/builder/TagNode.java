// ***************************************************************************
// Copyright (c) 2013, Industrial Logic, Inc., All Rights Reserved.
//
// This code is the exclusive property of Industrial Logic, Inc. It may ONLY be
// used by students during Industrial Logic's workshops or by individuals
// who are being coached by Industrial Logic on a project.
//
// This code may NOT be copied or used for any other purpose without the prior
// written consent of Industrial Logic, Inc.
// ****************************************************************************

package com.industriallogic.builder;

import java.util.ArrayList;
import java.util.List;

public class TagNode {
	String name;
	List<Attribute> attributes;
	List<TagNode> children;
	String value;

	public TagNode(String name) {
		this.name = name;
		children = new ArrayList<TagNode>();
		attributes = new ArrayList<Attribute>();
	}

	public void add(TagNode childNode) {
		children.add(childNode);
	}

	public void addAttribute(String attribute, String value) {
		attributes.add(new Attribute(attribute, value));
	}

	public void addValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		TagPrinter result = new TagPrinter();
		writeMarkupTo(result);
		return result.toString();
	}
	
	public String getName() {
		return name;
	}

	public String getAttributeNamed(String key) {
		for (Attribute attribute : attributes)
			if (attribute.name().equals(key))
				return attribute.value();
		return null;
	}

	public String getValue() {
		if (hasValue())
			value = rightTrimmedValue();
		return value;
	}

	boolean hasValue() {
		return null != value && !value.equals("");
	}
	
	protected void writeMarkupTo(TagPrinter result) {
		writeOpenTagTo(result);
		writeValueTo(result);
		writeChildrenTo(result);
		writeCloseTagTo(result);
	}

	private String rightTrimmedValue() {
		return value.replaceAll("\\s+$", "");
	}

	private void writeCloseTagTo(TagPrinter result) {
		result.writeCloseTagFor(this);
	}

	private void writeOpenTagTo(TagPrinter result) {
		result.writeOpenTagFor(this);
	}

	private void writeChildrenTo(TagPrinter result) {
		result.writeChildrenFor(this);
	}

	private void writeValueTo(TagPrinter result) {
		result.writeValueFor(this);
	}
}
