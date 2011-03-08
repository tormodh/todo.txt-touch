/**
 *
 * Todo.txt Touch/src/com/todotxt/todotxttouch/test/LinkParserTest.java
 *
 * Copyright (c) 2009-2011 Stephen Henderson, Tim Barlotta, Florian Behr
 *
 * LICENSE:
 *
 * This file is part of Todo.txt Touch, an Android app for managing your todo.txt file (http://todotxt.com).
 *
 * Todo.txt Touch is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any
 * later version.
 *
 * Todo.txt Touch is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with Todo.txt Touch.  If not, see
 * <http://www.gnu.org/licenses/>.
 * 
 * A JUnit based test class for the ContextParser
 * 
 * @author Tim Barlotta <tim[at]barlotta[dot]net>
 * @author Stephen Henderson <me[at]steveh[dot]ca>
 * @author Florian Behr <mail[at]florianbehr[dot]de>
 * @license http://www.gnu.org/licenses/gpl.html
 * @copyright 2009-2011 Stephen Henderson, Tim Barlotta 
 */
package com.todotxt.todotxttouch.task;

import junit.framework.TestCase;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

public class LinkParserTest extends TestCase {
	public void test_empty() {
		String input = "";
		List<URL> links = LinkParser.getInstance().parse(input);
		assertEquals(Collections.<URL> emptyList(), links);
	}

	public void test_null() {
		String input = null;
		List<URL> links = LinkParser.getInstance().parse(input);
		assertEquals(Collections.<URL> emptyList(), links);
	}

	public void test_withoutLink() {
		String input = "a simple string";
		List<URL> links = LinkParser.getInstance().parse(input);
		assertEquals(Collections.<URL> emptyList(), links);
	}

	public void test_withLinkHttp() throws MalformedURLException {
		String input = "a simple string with http://i.am.url";
		List<URL> links = LinkParser.getInstance().parse(input);
		assertEquals(1, links.size());
		assertTrue(links.contains(new URL("http://i.am.url")));
	}

	public void test_withLinkHttps() throws MalformedURLException {
		String input = "a simple string with https://i.am.url";
		List<URL> links = LinkParser.getInstance().parse(input);
		assertEquals(1, links.size());
		assertTrue(links.contains(new URL("https://i.am.url")));
	}

	public void test_withMultipleLinks() throws MalformedURLException {
		String input = "this is a text with http://a.url and https://a.nother.url";
		List<URL> links = LinkParser.getInstance().parse(input);
		assertEquals(2, links.size());
		assertTrue(links.contains(new URL("http://a.url")));
		assertTrue(links.contains(new URL("https://a.nother.url")));
	}

	public void test_withInterspersedLinks() throws MalformedURLException {
		String input = "this string http://has.a variety of urls https://beginning.with the http://and.also.the https://protocol.eof";
		List<URL> links = LinkParser.getInstance().parse(input);
		assertEquals(4, links.size());
		assertTrue(links.contains(new URL("http://has.a")));
		assertTrue(links.contains(new URL("https://beginning.with")));
		assertTrue(links.contains(new URL("http://and.also.the")));
		assertTrue(links.contains(new URL("https://protocol.eof")));
	}
}
