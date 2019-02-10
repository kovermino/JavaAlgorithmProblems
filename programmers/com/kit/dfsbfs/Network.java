package com.kit.dfsbfs;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Stack;

import org.junit.jupiter.api.Test;

class Network {
	
	/**
	 * 문제 설명
	 * 
	 * 네트워크란 컴퓨터 상호 간에 정보를 교환할 수 있도록 연결된 형태를 의미합니다. 
	 * 예를 들어, 컴퓨터 A와 컴퓨터 B가 직접적으로 연결되어있고, 컴퓨터 B와 컴퓨터 C가 직접적으로 연결되어 있을 때 컴퓨터 A와 컴퓨터 C도 간접적으로 연결되어 정보를 교환할 수 있습니다. 
	 * 따라서 컴퓨터 A, B, C는 모두 같은 네트워크 상에 있다고 할 수 있습니다.
	 * 컴퓨터의 개수 n, 연결에 대한 정보가 담긴 2차원 배열 computers가 매개변수로 주어질 때, 네트워크의 개수를 return 하도록 solution 함수를 작성하시오.
	 * 
	 * <제한사항>
	 * •컴퓨터의 개수 n은 1 이상 200 이하인 자연수입니다.
	 * •각 컴퓨터는 0부터 n-1인 정수로 표현합니다.
	 * •i번 컴퓨터와 j번 컴퓨터가 연결되어 있으면 computers[i][j]를 1로 표현합니다.
	 * •computer[i][i]는 항상 1입니다.
	 */

	@Test
	void test1() {
		int n = 3;
		int[][] computers = {{1,1,0},{1,1,0},{0,0,1}};
		assertEquals(2, solution(n, computers));
	}
	
	@Test
	void test2() {
		int n = 3;
		int[][] computers = {{1,1,0},{1,1,1},{0,1,1}};
		assertEquals(1, solution(n, computers));
	}
	
	/**
	 * 리팩토링된 버전
	 * 
	 * @param n			: 컴퓨터의 개수
	 * @param computers : 연결된 컴퓨터 맵
	 * @return			: 네트워크의 개수
	 */
	public int solution(int n, int[][] computers) {
		boolean[] visited = new boolean[n];
		int count = 0;
		for(int i=0;i<n;i++){
			if(!visited[i]) {
				checkConnectedNetwork(i, computers, visited);
				count++;
			}
		}
		return count;
	}
	
	/** 
	 * @param startNode	: 탐색이 시작될 노드
	 * @param computers	: 연결된 컴퓨터 맵
	 * @param visited	: 방문한 노드를 표시하는 배열
	 */
	public void checkConnectedNetwork(int startNode, int[][] computers, boolean[] visited) {
		Stack<Integer> stack = new Stack<>();
		stack.push(startNode);
		while(!stack.empty()) {
			int current = stack.pop();
			if(!visited[current]) {
				visited[current] = true;
				addConnectedNodesToStack(current, computers[current], stack);
			}
		}
	}
	
	/**
	 * @param current	: 현재 방문중인 노드
	 * @param computers	: 연결된 컴퓨터 맵
	 * @param stack		: 탐색 대상 노드를 담을 스택
	 */
	public void addConnectedNodesToStack(int current, int[] computers, Stack<Integer> stack) {
		for(int i=0;i<computers.length;i++) {
			if(i!=current && computers[i]==1) {
				stack.push(i);
			}
		}
	}
	
	public int reSolution(int n, int[][] computers) {
		boolean[] visited = new boolean[n];
		int count = 0;
		for(int i=0;i<n;i++) {
			if(!visited[i]) {
				Stack<Integer> stack = new Stack<>();
				stack.push(i);
				while(!stack.isEmpty()) {
					int current = stack.pop();
					if(!visited[current]) {
						visited[current] = true;
						for(int j=0;j<n;j++) {
							if(j!=current && computers[current][j]==1) {
								stack.push(j);
							}
						}
					}
				}
				count++;
			}
		}
		return count;
	}
	
	/**
	 * 최초 솔루션(리팩토링 하기 전)
	 * 
	 * @param n			: 노드의 개수
	 * @param computers	: 연결된 컴퓨터 맵
	 * @return			: 네트워크의 개수
	 */
	public int previousSolution(int n, int[][] computers) {
		boolean[] visited = new boolean[n];
		int count = 0;
		for(int i=0;i<n;i++) {
			if(!visited[i]) {
				Stack<Integer> stack = new Stack<>();
				stack.push(i);
				while(!stack.empty()) {
					int current = stack.pop();
					if(!visited[current]) {
						visited[current] = true;
						for(int j=0;j<n;j++) {
							if(j!=current&&computers[current][j]==1) {
								stack.push(j);
							}
						}
					}
				}
				count++;
			}
		}
		return count;
	}
}
