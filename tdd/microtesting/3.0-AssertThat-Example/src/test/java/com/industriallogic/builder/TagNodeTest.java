// ***************************************************************************
// Copyright (c) 2018, Industrial Logic, Inc., All Rights Reserved.
//
// This code is the exclusive property of Industrial Logic, Inc. It may ONLY be
// used by students during Industrial Logic's workshops or by individuals
// who are being coached by Industrial Logic on a project.
//
// This code may NOT be copied or used for any other purpose without the prior
// written consent of Industrial Logic, Inc.
// ****************************************************************************

package com.industriallogic.builder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import org.junit.Test;


public class TagNodeTest {
	@Test
	public void rendersOneElement() {
		assertThat(new TagNode("orders").toString())
		  .isEqualTo("<orders/>");
	}
	
	@Test
	public void rendersElementWithAttribute() {
		TagNode order  = new TagNode("order");
		order.addAttribute("number", "123");
	
		assertThat(order.toString())
		  .isEqualTo("<order number=\"123\"/>");
	}
	
	@Test
	public void addsAttributesToArray() {
		TagNode order  = new TagNode("order");
		order.addAttribute("number", "123");
		order.addAttribute("another", "135");
		
		assertThat(order.attributes)
		  .extracting("name", "value")
		  .containsExactly(
		      tuple("number", "123"),
		      tuple("another", "135"));
	}
	
	@Test
	public void getsAttributeValuesByName() {
		TagNode root = new TagNode("root");
		root.addAttribute("attributeName1", "value 1");
		root.addAttribute("attributeName2", "value 2");
		
		assertThat(root.getAttributeNamed("attributeName1"))
		  .isEqualTo("value 1");
		assertThat(root.getAttributeNamed("attributeName2"))
		  .isEqualTo("value 2");
	}
	
	@Test
	public void rendersElementWithValueAndAttribute() {
		TagNode order  = new TagNode("order");
		order.addAttribute("number", "123");
		order.addValue("carDoor");
		
    String expected =
    "<order number=\"123\">" +
        "carDoor" + 
    "</order>";

    assertThat(order.toString())
		  .isEqualTo(expected);
	}
	
	@Test
	public void getsValue() {
		TagNode root = new TagNode("root");
		root.addValue("a value");
		
		assertThat(root.getValue())
		  .isEqualTo("a value");
	}
	
	@Test
	public void nestsChildrenCorrectly() {
		TagNode orders = new TagNode("orders");
  		orders.add(new TagNode("order1"));
  		orders.add(new TagNode("order2"));
  		orders.add(new TagNode("order3"));
  		TagNode order4 = new TagNode("order4");
  		  order4.add(new TagNode("order4-child"));
  		orders.add(order4);
		
		assertThat(orders.children.size())
		  .isEqualTo(4);
		assertThat(orders.children.get(3).getName())
		  .isEqualTo("order4");
		assertThat(orders.children.get(3).children.get(0).getName())
		  .isEqualTo("order4-child");
	}
}
