package com.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.StringTokenizer;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.list.ListNode;

class ListNodeTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		
		// list 구성
		node1.next = node2;
		node2.next = node3;
		node3.next = null;
		
		// list 맨 앞에 원소를 추가
		ListNode node0 = new ListNode(0, node1);
	}
	
	@Test
	void test() {
		StringTokenizer st = new StringTokenizer("(cool)dksl asdkjl dsa", " ()", true);
		while(st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
		System.out.println();
	}

}
