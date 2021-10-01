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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TagNodeTest {
	
	@Test
	public void testOneElementTree() {
		String expected = "<orders/>";
		TagNode orders = new TagNode("orders");
		assertEquals(expected, orders.toString());
	}
	
	@Test
	public void testTreeWithAttributes() {
		String expected = "<order number=\"123\"/>";
		TagNode order  = new TagNode("order");
		order.addAttribute("number", "123");
		assertEquals(expected, order.toString());
	}
	
	@Test
	public void testTreeWithValues() {
		String expected =
		"<order number=\"123\">" +
				"carDoor" + 
		"</order>";
		TagNode order  = new TagNode("order");
		order.addAttribute("number", "123");
		order.addValue("carDoor");
		assertEquals(expected, order.toString());
	}
	
	@Test
	public void testObtainAttributes() {
		TagNode root = new TagNode("root");
		root.addAttribute("attributeName1", "value 1");
		root.addAttribute("attributeName2", "value 2");
		
		assertEquals("value 1", root.getAttributeNamed("attributeName1"));
		assertEquals("value 2", root.getAttributeNamed("attributeName2"));
	}	
	
	@Test
	public void testObtainValue() {
		TagNode root = new TagNode("root");
		String EXPECTED_VALUE = "a value";
		root.addValue(EXPECTED_VALUE);
		assertEquals(EXPECTED_VALUE, root.getValue());
	}
	
	@Test
	public void testChildren() {
		TagNode orders = new TagNode("orders");
		orders.add(new TagNode("order1"));
		orders.add(new TagNode("order2"));
		orders.add(new TagNode("order3"));
		TagNode order4 = new TagNode("order4");
		order4.add(new TagNode("order4-child"));
		orders.add(order4);
		
		assertEquals(4,orders.children.size());
		assertEquals("order4",orders.children.get(3).getName());
		assertEquals("order4-child",orders.children.get(3).children.get(0).getName());
	}
}
