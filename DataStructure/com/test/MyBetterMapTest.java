package com.test;

import org.junit.Before;
import com.map.MyBetterMap;

public class MyBetterMapTest extends MyLinearMapTest {

	@Before
	public void setUp() throws Exception {
		map = new MyBetterMap<String, Integer>();
		map.put("One", 1);
		map.put("Two", 2);
		map.put("Three", 3);
		map.put(null, 0);
	}
}