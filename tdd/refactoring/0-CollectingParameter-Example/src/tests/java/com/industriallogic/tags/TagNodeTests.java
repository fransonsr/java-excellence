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

import com.industriallogic.tags.TagNode;
import junit.framework.TestCase;

public class TagNodeTests extends TestCase {
	public void testTagNoAttributesOrValue() {
		TagNode priceTag = new TagNode("price");
		String expected = 
			"<price>" +
			"</price>";
		assertEquals("price XML", expected, priceTag.toString());
	}
	
	public void testTagWithAttributes() {
		String expected =
		"<orders>" +
			"<order number='123'>" +
			"</order>" +
		"</orders>";
		TagNode orders = new TagNode("orders");
		TagNode order  = new TagNode("order");
		order.addAttribute("number", "123");
		orders.add(order);
		assertEquals("orders xml", expected, orders.toString());
	}
	
	public void testTagWithOneAttributeAndValue() {
		TagNode priceTag = new TagNode("price");
		priceTag.addAttribute("currency", "USD");
		priceTag.addValue("8.95");
		String expected = 
			"<price currency=" +
			"'" +
			"USD" +
			"'>" +
			"8.95" +
			"</price>";
		assertEquals("price XML", expected, priceTag.toString());
	}
	
	public void testTagTwoAttributesNoValue() {
		TagNode priceTag = new TagNode("price");
		priceTag.addAttribute("name1", "value1");
		priceTag.addAttribute("name2", "value2");
		String expected = 
			"<price name1='value1' name2='value2'>" +
			"</price>";
		assertEquals("price XML", expected, priceTag.toString());
	}
	
	public void testCompositeTagOneChild() {
		TagNode productTag = new TagNode("product");
		productTag.add(new TagNode("price"));
		String expected =
			"<product>" +
				"<price>" +
				"</price>" +
			"</product>";
		assertEquals("product XML", expected, productTag.toString());
	}
	
	public void testGrandchildren() {
		TagNode orderTag = new TagNode("order");
		TagNode productTag = new TagNode("product");
		orderTag.add(productTag);
		productTag.add(new TagNode("price"));
		String expected =
			"<order>" +
				"<product>" +
					"<price>" +
					"</price>" +
				"</product>" +
			"</order>";
		assertEquals("order XML", expected, orderTag.toString());
	}
}
