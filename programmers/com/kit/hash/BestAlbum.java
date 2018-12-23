package com.kit.hash;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

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
		NavigableMap<Integer, Chart> genreMap = treeMapOrderByTotalPlays(getGenreMap(genres, plays));
		List<Integer> result = new LinkedList<>();
		for(Integer totalPlays: genreMap.descendingKeySet()) {
			Chart current = genreMap.get(totalPlays);
			current.addTopTwo(result);
		}
		return result.stream().mapToInt(i->i).toArray();
	}
	
	/**
	 * @param genreMap	: 장르별 차트가 들어있는 HashMap
	 * @return			: 총 재생수 별 차트가 들어있는 TreeMap
	 */
	public NavigableMap<Integer, Chart> treeMapOrderByTotalPlays(Map<String, Chart> genreMap){
		NavigableMap<Integer, Chart> tree = new TreeMap<>();
		for(String genre: genreMap.keySet()) {
			Chart current = genreMap.get(genre);
			tree.put(current.totalPlays, current);
		}
		return tree;
	}
	
	/**
	 * @param genres	: 해당 인덱스에 해당하는 곡의 장르가 들어있는 문자열 배열
	 * @param plays		: 해당 인덱스에 해당하는 곡의 재생 수가 들어있는 정수 배열
	 * @return			: 장르별 차트가 들어있는 HashMap
	 */
	public Map<String, Chart> getGenreMap(String[] genres, int[] plays){
		Map<String, Chart> genreMap = new HashMap<>();
		for(int i=0;i<genres.length;i++) {
			Chart currentChart = genreMap.getOrDefault(genres[i], new Chart(genres[i]));
			currentChart.add(new Song(i, plays[i]));
			genreMap.put(genres[i], currentChart);
		}
		return genreMap;
	}
	
	/**
	 * @author 윤종우
	 * 장르별 총 재생 수와 곡들의 인덱스 Set
	 */
	public class Chart{
		String genre;
		Integer totalPlays;
		Set<Song> songs;
		
		public Chart(String genre) {
			this.genre = genre;
			this.totalPlays = 0;
			songs = new TreeSet<Song>();
		}
		
		public void add(Song s) {
			this.totalPlays += s.play;
			songs.add(s);
		}
		
		public void addTopTwo(List<Integer> list) {
			int index = 0;
			for(Song s: songs) {
				list.add(s.id);
				index++;
				if(index==2) {
					break;
				}
			}
		}
	}
	/**
	 * @author 윤종우
	 * 재생수가 많은 것이 앞쪽으로, 재생수가 같다면 아이디가 낮은 것이 앞쪽으로 정렬
	 */
	public class Song implements Comparable<Song>{
		Integer id;
		Integer play;
		
		public Song(int id, int play) {
			this.id = id;
			this.play = play;
		}

		@Override
		public int compareTo(Song s) {
			if(this.play.equals(s.play)) {
				return this.id.compareTo(s.id);
			}
			return s.play.compareTo(this.play);
		}
	}
	
}
