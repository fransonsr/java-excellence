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

public class TagPrinter {
	private StringBuilder result = new StringBuilder("");

	protected void append(String contentToAppend) {
		result.append(contentToAppend);
	}

	protected void writeCloseTagFor(TagNode node) {
		if (!(node.hasChildren() || node.hasValueWithZeroLength())) 
			return;
		
		append("</");
		append(node.getName());
		append(">");
	}

	protected void writeOpenTagFor(TagNode node) {
		append("<");
		append(node.getName());
		for (Attribute attribute : node.attributes())
			append(attribute.toString());
		if (node.hasChildren() || node.hasValueWithZeroLength())
			append(">");
		else
			append("/>");
	}

	protected void writeValueFor(TagNode node) {
		if (node.hasValue())
			append(node.getValue());
	}

	protected void writeChildrenFor(TagNode parentNode) {
		for (TagNode node : parentNode.children)
			writeChildFor(node);
	}

	protected void writeChildFor(TagNode node) {
		node.writeMarkupTo(this);
	}
	
	public String toString() {
		return result.toString();
	}
}
