package com.test;

import org.junit.Before;

import com.map.MyHashMap;

public class MyHashMapTest extends MyLinearMapTest {

	@Before
	public void setUp() throws Exception {
		map = new MyHashMap<String, Integer>();
		map.put("One", 1);
		map.put("Two", 2);
		map.put("Three", 3);
		map.put(null, 0);
	}
}