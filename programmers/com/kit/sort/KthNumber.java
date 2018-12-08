package com.kit.sort;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * 배열 array의 i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때, k번째에 있는 수를 구하려 합니다.
 * 예를 들어 array가 [1, 5, 2, 6, 3, 7, 4], i = 2, j = 5, k = 3이라면
 * 
 * 1.array의 2번째부터 5번째까지 자르면 [5, 2, 6, 3]입니다.
 * 2.1에서 나온 배열을 정렬하면 [2, 3, 5, 6]입니다.
 * 3.2에서 나온 배열의 3번째 숫자는 5입니다.
 * 
 * 배열 array, [i, j, k]를 원소로 가진 2차원 배열 commands가 매개변수로 주어질 때, commands의 모든 원소에 대해 앞서 설명한 연산을 적용했을 때 나온 결과를 배열에 담아 return 하도록 solution 함수를 작성해주세요.
 * 
 * <제한사항>
 * •array의 길이는 1 이상 100 이하입니다.
 * •array의 각 원소는 1 이상 100 이하입니다.
 * •commands의 길이는 1 이상 50 이하입니다.
 * •commands의 각 원소는 길이가 3입니다.
*/

class KthNumber {

	@Test
	void getPartOfArray() throws Exception {
		int[] array = {1, 5, 2, 6, 3, 7, 4};
		int[] result = {5, 2, 6, 3};
		assertArrayEquals(result, getPartOfArray(array, 2, 5));
	}
	
	@Test
	void solution() throws Exception {
		int[] array = {1, 5, 2, 6, 3, 7, 4};
		int[][] commands = {{2, 5, 3},{4, 4, 1},{1, 7, 3}};
		int[] result = {5, 6, 3};
		assertArrayEquals(result, solution(array, commands));
	}
	
	/**
	 * @param array		: 작업 대상 배열
	 * @param commands	: 명령집합(i, j, k)
	 * @return			: 배열에 대해 각 명령을 수행한 후의 결과 값을 모은 배열
	 * @throws Exception 
	 */
	public int[] solution(int[] array, int[][] commands) throws Exception {
		int[] result = new int[commands.length];
		for(int i=0;i<commands.length;i++) {
			int[] temp = getPartOfArray(array, commands[i][0], commands[i][1]);
			Arrays.sort(temp);
			result[i] = temp[commands[i][2]-1];
		}
		return result;
	}
	
	/**
	 * @param array : 작업 대상 배열
	 * @param i		: 시작 인덱스
	 * @param j		: 끝 인덱스
	 * @return		: 시작부터 끝까지 잘린 배열
	 * @throws Exception 
	 */
	public int[] getPartOfArray(int[] array, int i, int j) throws Exception {
		if(i>j) {
			throw new Exception("begin index is bigger than end index");
		}
		int[] result = new int[j-i+1];
		for(int index=i-1;index<=j-1;index++) {
			result[index-i+1] = array[index];
		}
		return result;
	}
	
	

}
