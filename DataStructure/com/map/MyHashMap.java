package com.map;

import java.util.List;
import java.util.Map;


public class MyHashMap<K, V> extends MyBetterMap<K, V> implements Map<K, V> {

	// 하위 맵당 평균 엔트리의 개수(로드팩터;load factor)
	protected static final double FACTOR = 1.0;

	@Override
	public V put(K key, V value) {
		V oldValue = super.put(key, value);
		// 전체 요소의 개수가 하위 맵의 개수보다 많아지면(즉 엔트리:하위맵 = 1:1이 아니면) 재해시
		if (size() > maps.size() * FACTOR) {
			rehash();
		}
		return oldValue;
	}

	protected void rehash() {
		List<MyLinearMap<K, V>> oldMap = maps;
		int newK = maps.size() * 2;
		makeMaps(newK);
		for(MyLinearMap<K, V> map: oldMap) {
			for(K key: map.keySet()) {
				put(key, map.get(key));
			}
		}
	}

	public static void main(String[] args) {
		Map<String, Integer> map = new MyHashMap<String, Integer>();
		for (int i=0; i<10; i++) {
			map.put(new Integer(i).toString(), i);
		}
		Integer value = map.get("3");
		System.out.println(value);
	}
}