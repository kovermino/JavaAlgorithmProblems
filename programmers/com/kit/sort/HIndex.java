package com.kit.sort;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * H-Index는 과학자의 생산성과 영향력을 나타내는 지표입니다. 
 * 어느 과학자의 H-Index를 나타내는 값인 h를 구하려고 합니다. 
 * 위키백과(https://en.wikipedia.org/wiki/H-index)에 따르면, H-Index는 다음과 같이 구합니다.
 * 어떤 과학자가 발표한 논문 n편 중, h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용되었다면 h가 이 과학자의 H-Index입니다.
 * 어떤 과학자가 발표한 논문의 인용 횟수를 담은 배열 citations가 매개변수로 주어질 때, 이 과학자의 H-Index를 return 하도록 solution 함수를 작성해주세요.
 * 
 * <제한사항>
 * •과학자가 발표한 논문의 수는 1편 이상 1,000편 이하입니다.
 * •논문별 인용 횟수는 0회 이상 10,000회 이하입니다.
 */

import org.junit.jupiter.api.Test;

class HIndex {

	@Test
	void solution() {
		int[] citations = {3, 0, 6, 1, 5};
		int result = 3;
		assertEquals(result, solution(citations));
	}
	
	/**
	 * @param citations	: 논문 인용 횟수
	 * @return			: H-index(h번 이상 인용된 논문이 h편 이상인 인덱스 중 가장 큰 값)
	 */
	public int solution(int[] citations) {
		int result = 0;
		List<Integer> list = Arrays.stream(citations).mapToObj(i->i).collect(Collectors.toList());
		Collections.sort(list, Collections.reverseOrder());
		for(int i=0;i<list.size();i++) {
			// list.get(i):인용된 논문 숫자, i+1:인덱스
			if(list.get(i)>=i+1) {
				result = i+1;
			}else {
				break;
			}
		}
		return result;
	}

}
