package com.lind.common.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 主要体现了有序性，hashmap是无序性，即写和读的顺序不能保持一致. LinkHashMap保持迭代的顺序等于插入时的顺序
 */
public class LinkHashMapAndHashMapTest {

	@Test
	public void linkHashMap() {
		Map<String, String> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("name1", "josan1");
		linkedHashMap.put("name2", "josan2");
		linkedHashMap.put("name3", "josan3");
		Set<Map.Entry<String, String>> set = linkedHashMap.entrySet();
		Iterator<Map.Entry<String, String>> iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = iterator.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			System.out.println("key:" + key + ",value:" + value);
		}
	}

	@Test
	public void hashMap() {
		Map<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("name1", "josan1");
		hashMap.put("name2", "josan2");
		hashMap.put("name3", "josan3");
		Set<Map.Entry<String, String>> set = hashMap.entrySet();
		Iterator<Map.Entry<String, String>> iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = iterator.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			System.out.println("key:" + key + ",value:" + value);
		}
	}

}
