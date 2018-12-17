package com.kit.hash;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

class BestAlbum {
	
	/**
	 * 문제 설명
	 * 
	 * 스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다. 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.
	 * 
	 * 1.속한 노래가 많이 재생된 장르를 먼저 수록합니다.
	 * 2.장르 내에서 많이 재생된 노래를 먼저 수록합니다.
	 * 3.장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
	 * 
	 * 노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때, 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.
	 * 
	 * <제한사항>
	 * •genres[i]는 고유번호가 i인 노래의 장르입니다.
	 * •plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
	 * •genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
	 * •장르 종류는 100개 미만입니다.
	 * •장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
	 * •모든 장르는 재생된 횟수가 다릅니다.
	 */

	@Test
	void test1() {
		String[] genres = {"classic", "pop", "classic", "classic", "pop"};
		int[] plays = {500, 600, 150, 800, 2500};
		int[] result = {4, 1, 3, 0};
		
		assertArrayEquals(result, solution(genres, plays));
	}
	
	/**
	 * @param genres	: 장르 목록
	 * @param plays		: 재생횟수 목록
	 * @return			: 장르별 재생횟수가 가장 많은 2곡의 인덱스를 순서대로 담은 정수 배열
	 */
	public int[] solution(String[] genres, int[] plays) {
		Map<String, SongsForGenre> playCount = new HashMap<>();
		for(int i=0;i<genres.length;i++) {
			SongsForGenre temp = playCount.getOrDefault(genres[i], new SongsForGenre());
			temp.add(new Song(i, plays[i]));
			playCount.put(genres[i], temp);
		}
		
		Map<Integer, String> tree = new TreeMap<>();
		for(String key: playCount.keySet()) {
			tree.put(playCount.get(key).totalPlays, key);
		}
		
		List<Integer> result = new LinkedList<>();
		for(int totalPlays : tree.keySet()) {
			int[] temp = playCount.get(tree.get(totalPlays)).getTopTwo();
			if(temp[1]!=-1) {
				result.add(0, temp[1]);
			}
			if(temp[0]!=-1) {
				result.add(0, temp[0]);
			}
		}
		
		return result.stream().mapToInt(i->i).toArray();
	}
	
	class SongsForGenre{
		Integer totalPlays;
		List<Song> songs;
		
		public SongsForGenre() {
			totalPlays = 0;
			songs = new ArrayList<>();
		}
		
		public void add(Song s) {
			totalPlays+=s.plays;
			songs.add(s);
		}
		
		public int[] getTopTwo() {
			int i1 = -1;
			int i2 = -1;
			if(songs.size()==0) {
				return new int[] {-1, -1};
			}else {
				Collections.sort(songs);
				i1 = songs.get(0).index;
				i2 = songs.size()>1?songs.get(1).index:-1;
			}
			return new int[] {i1, i2};
		}
	}
	
	class Song implements Comparable<Song>{
		Integer index;
		Integer plays;
		
		public Song(int index, int plays){
			this.index = index;
			this.plays = plays;
		}
		
		@Override
		public int compareTo(Song s) {
			if(this.plays==s.plays) {
				return this.index.compareTo(s.index);
			}
			return s.plays.compareTo(this.plays);
		}
	}
	
}
