package com.lind.common.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 集合可排序
 */
public class SortedTest {
  @org.junit.Test
  public void sortAndIterator() {
    HashMap<String, String> map = new HashMap<>();
    map.put("3", "33");
    map.put("1", "11");
    map.put("2", "22");
    map.put("a", "aa");

    for (Map.Entry<String, String> entry : map.entrySet()) {
      System.out.println("排序之前:" + entry.getKey() + " 值" + entry.getValue());
    }
    System.out.println("======================================================");
    SortedMap<String, String> sort = new TreeMap<>(map);
    Set<Map.Entry<String, String>> entry1 = sort.entrySet();
    Iterator<Map.Entry<String, String>> it = entry1.iterator();
    while (it.hasNext()) {
      Map.Entry<String, String> entry = it.next();
      System.out.println("排序之后:" + entry.getKey() + " 值" + entry.getValue());
    }
    /**
     * 输出结果:
     排序之前:1 值11
     排序之前:a 值aa
     排序之前:2 值22
     排序之前:3 值33
     ======================================================
     排序之后:1 值11
     排序之后:2 值22
     排序之后:3 值33
     排序之后:a 值aa
     */
  }

  @org.junit.Test
  public void get() {
    SortedMap<String, String> map = null;
    map = new TreeMap<String, String>();   //通过子类实例化接口对象
    map.put("D", "DDDDD");
    map.put("A", "AAAAA");
    map.put("C", "CCCCC");
    map.put("B", "BBBBB");
    System.out.println("第一个元素的key:" + map.firstKey());
    System.out.println("key对于的值为:" + map.get(map.firstKey()));
    System.out.println("最后一个元素的key:" + map.lastKey());
    System.out.println("key对于的值为:" + map.get(map.lastKey()));
    System.out.println("返回小于指定范围的集合（键值小于“C”）");
    for (Map.Entry<String, String> me : map.headMap("C").entrySet()) {
      System.out.println("\t|- " + me.getKey() + "-->" + me.getValue());
    }
    System.out.println("返回大于指定范围的集合（键值大于等于“C”）");
    for (Map.Entry<String, String> me : map.tailMap("C").entrySet()) {
      System.out.println("\t|- " + me.getKey() + "-->" + me.getValue());
    }
    System.out.println("返回部分集合（键值“B”和“D”之间,包括B不包括D）");
    for (Map.Entry<String, String> me : map.subMap("B", "D").entrySet()) {
      System.out.println("\t|- " + me.getKey() + "-->" + me.getValue());
    }
    /**
     * 第一个元素的key:A
     key对于的值为:AAAAA
     最后一个元素的key:D
     key对于的值为:DDDDD
     返回小于指定范围的集合（键值小于“C”）
     |- A-->AAAAA
     |- B-->BBBBB
     返回大于指定范围的集合（键值大于等于“C”）
     |- C-->CCCCC
     |- D-->DDDDD
     返回部分集合（键值“B”和“D”之间,包括B不包括D）
     |- B-->BBBBB
     |- C-->CCCCC

     */
  }


}
