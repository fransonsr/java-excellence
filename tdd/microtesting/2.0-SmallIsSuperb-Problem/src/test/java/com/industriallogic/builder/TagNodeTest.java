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
	public void node()
	{
		String expected = "<orders/>";
		TagNode orders = new TagNode("orders");
		assertEquals("orders xml", expected, orders.toString());
		
		// add a couple of attributes
		orders.addAttribute("Date", "20130204");
		orders.addAttribute("OrderCount", "453");
		assertEquals("<orders Date=\"20130204\" OrderCount=\"453\"/>",orders.toString());

		assertEquals("20130204", orders.getAttributeNamed("Date"));
		assertEquals("453", orders.getAttributeNamed("OrderCount"));
		assertEquals(2,orders.attributes.size());
		
		expected =
		"<orders Date=\"20130204\" OrderCount=\"453\">" +
			"<order number=\"123\">" +
			"carDoor" + 
			"</order>" +
		"</orders>";

		TagNode order  = new TagNode("order");
		order.addAttribute("number", "123");
		order.addValue("carDoor");
		orders.add(order);
		assertEquals("orders xml", expected, orders.toString());
		
		String EXPECTED_VALUE = "a value";
		orders.addValue(EXPECTED_VALUE);
		assertEquals(EXPECTED_VALUE, orders.getValue());
		
		orders.add(new TagNode("order1"));
		orders.add(new TagNode("order2"));
		orders.add(new TagNode("order3"));
		
		TagNode order4 = new TagNode("order4");
		order4.add(new TagNode("order4-child"));
		orders.add(order4);
		
		assertEquals(5,orders.children.size());
		assertEquals("order4",orders.children.get(4).getName());
		assertEquals("order4-child",orders.children.get(4).children.get(0).getName());
	}
}
