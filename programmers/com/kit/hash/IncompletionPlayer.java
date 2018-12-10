package com.kit.hash;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class IncompletionPlayer {
	
	/**
	 * 문제 설명
	 * 수많은 마라톤 선수들이 마라톤에 참여하였습니다. 
	 * 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.
	 * 마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 주어질 때, 완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.
	 * 
	 * <제한사항>
	 * •마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.
	 * •completion의 길이는 participant의 길이보다 1 작습니다.
	 * •참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.
	 * •참가자 중에는 동명이인이 있을 수 있습니다.
	 */

	@Test
	void test1() {
		String[] participant = {"leo", "kiki", "eden"};
		String[] completion = {"eden", "kiki"};
		String result = "leo";
		assertEquals(result, solution(participant, completion));
		assertEquals(result, betterSolution(participant, completion));
	}
	
	@Test
	void test2() {
		String[] participant = {"marina", "josipa", "nikola", "vinko", "filipa"};
		String[] completion = {"josipa", "filipa", "marina", "nikola"};
		String result = "vinko";
		assertEquals(result, solution(participant, completion));
		assertEquals(result, betterSolution(participant, completion));
	}
	
	@Test
	void test3() {
		String[] participant = {"mislav", "stanko", "mislav", "ana"};
		String[] completion = {"stanko", "ana", "mislav"};
		String result = "mislav";
		assertEquals(result, solution(participant, completion));
		assertEquals(result, betterSolution(participant, completion));
	}
	
	/**
	 * @param participant	: 경기에 참가한 모든 선수들의 이름 배열
	 * @param completion	: 완주한 선수들의 이름 배열
	 * @return				: 완주하지 못한 선수의 이름 문자열
	 */
	public String solution(String[] participant, String[] completion) {
		Map<String, Integer> players = getMapOfPlayers(participant);
		for(String key: completion) {
			if(players.containsKey(key)) {
				int temp = players.get(key);
				temp--;
				if(temp==0) {
					players.remove(key);
				}else {
					players.put(key, temp);
				}
			}
		}
		String result = players.keySet().iterator().next();
		return result;
	}
	
	/**
	 * @param players	: 선수들의 이름 배열
	 * @return			: 이름/인원의 HashMap
	 */
	public Map<String, Integer> getMapOfPlayers(String[] players){
		Map<String, Integer> result = new HashMap<>();
		for(String key: players) {
			if(result.containsKey(key)) {
				int temp = result.get(key);
				temp++;
				result.put(key, temp);
			}else {
				result.put(key, 1);
			}
		}
		return result;
	}
	
	/**
	 * solution 메서드와 똑같은 동작을 하지만 더 깔끔한 코드
	 * @param participant	: 경기에 참가한 모든 선수들의 이름 배열
	 * @param completion	: 완주한 선수들의 이름 배열
	 * @return				: 완주하지 못한 선수의 이름 문자열
	 */
	public String betterSolution(String[] participant, String[] completion) {
		Map<String, Integer> players = new HashMap<>();
		
		for(String key: participant) players.put(key, players.getOrDefault(key, 0)+1);
		for(String key: completion) players.put(key, players.get(key)-1);
		
		for(String key: players.keySet()) {
			if(players.get(key)>0) {
				return key;
			}
		}
		return "";
	}

}
