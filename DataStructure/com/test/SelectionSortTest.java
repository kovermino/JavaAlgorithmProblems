package com.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.sort.SelectionSort;

class SelectionSortTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void selectionSortTest() {
		int[] data = {5, 12, 4, 34, -5, 2, 9};
		int[] result = {-5, 2, 4, 5, 9, 12, 34};
		SelectionSort.selectionSort(data);
		
		assertArrayEquals(result, data);
	}
	
	@Test
	void getIndexOfSmallestElementTest() {
		int[] data = {5, 12, 4, 34, -5, 2, 9};
		
		assertEquals(4, SelectionSort.getIndexOfSmallestElement(data, 0));
	}
	
	@Test
	void sortTest() {
		int[] data = {5, 12, 4, 34, -5, 2, 9};
		int[] result = {-5, 2, 4, 5, 9, 12, 34};
		SelectionSort.selectionSort(data);
		
		assertArrayEquals(result, data);
	}
	
	

}
