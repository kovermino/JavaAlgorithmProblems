package com.kit.sort;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

/**
 * 0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.
 * 예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고, 이중 가장 큰 수는 6210입니다.
 * 0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때, 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return 하도록 solution 함수를 작성해주세요.
 * 
 * <제한 사항>
 * •numbers의 길이는 1 이상 100,000 이하입니다.
 * •numbers의 원소는 0 이상 1,000 이하입니다.
 * •정답이 너무 클 수 있으니 문자열로 바꾸어 return 합니다.
 */

class LargestNumber {
	
	@Test
	void listToString() {
		List<String> list = Arrays.asList("6", "10", "2");
		String result = "6102";
		assertEquals(result, listToString(list));
	}

	@Test
	void solution() {
		int[] numbers = {6, 10, 2};
		String result = "6210";
		assertEquals(result, solution(numbers));
	}
	
	/**
	 * @param numbers	: 작업 대상 정수 배열
	 * @return			: 배열의 요소 정수를 이어붙여 만들 수 있는 가장 큰 수 문자열
	 */
	public String solution(int[] numbers) {
		List<String> temp = Arrays.stream(numbers).mapToObj(i->i+"").collect(Collectors.toList());
		Collections.sort(temp, (s1, s2)->(s2+s1).compareTo(s1+s2));
		if(temp.get(0).equals("0")) {
			return "0";
		}
		return listToString(temp);
	}
	
	/**
	 * @param list	: 문자열로 변환할 리스트
	 * @return		: 리스트를 순서대로 이어붙인 문자열
	 */
	public String listToString(List<String> list) {
		StringBuilder result = new StringBuilder();
		for(String s: list) {
			result.append(s);
		}
		return result.toString();
	}

}
