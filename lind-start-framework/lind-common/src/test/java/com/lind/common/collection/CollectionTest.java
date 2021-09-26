package com.lind.common.collection;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 集合操作
 */
public class CollectionTest {
    List<Student> students = new ArrayList<Student>();

    @Test
    public void test1() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);
        list2.add(5);

        System.out.println("====求交集===");
        List<Integer> list = list1.stream().filter(t -> list2.contains(t)).collect(Collectors.toList());
        list.stream().forEach(System.out::println);

        System.out.println("====求差集===");
        list = list1.stream().filter(t -> !list2.contains(t)).collect(Collectors.toList());
        list.stream().forEach(System.out::println);


        System.out.println("====求并集===");
        list.addAll(list1);
        list.addAll(list2);
        list = list.stream().distinct().collect(Collectors.toList());
        list.stream().forEach(System.out::println);
    }

    @Test
    public void itl() {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void filterField() {
        // mysql
        List<Column> columnVoList = new ArrayList<>();
        columnVoList.add(new Column("a1"));
        columnVoList.add(new Column("a2"));
        columnVoList.add(new Column("a3"));

        // http
        Map<String, Object> record = new HashMap<>();
        record.put("a1", "zhangsan");
        record.put("a2", "lisi");
        record.put("a4", "wangwu");
        columnVoList.stream()
                //取交集
                .filter(columnVo -> {
                    String columnName = columnVo.getName();
                    return record.containsKey(columnName);
                }).forEach(o -> {
            System.out.println(o.name);
        });

    }

    @Before
    public void init() {
        students.add(new Student(10, "zzl", 90));
        students.add(new Student(30, "lr", 98));
        students.add(new Student(9, "zhz", 90));
    }

    /**
     * 分组集合
     */
    @Test
    public void ListToMap() {
        ConcurrentHashMap<String, List<String>> concurrentHashMap;
        List<UserAccountSet> userAccountSets = Arrays.asList(
                new UserAccountSet("zzl", "a1"),
                new UserAccountSet("lisi", "a2"),
                new UserAccountSet("zhz", "a1")
        );
        concurrentHashMap = userAccountSets.stream().map(i ->
                ImmutableMap.of(i.getGroupAccountName(),
                        userAccountSets.stream().filter(o -> o.getGroupAccountName().equals(i.getGroupAccountName()))
                                .map(p -> p.getLoginAccount())
                                .collect(Collectors.toList()))).collect(ConcurrentHashMap::new, Map::putAll, Map::putAll);
        System.out.println(concurrentHashMap);
    }

    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    class UserAccountSet {
        private String loginAccount;
        private String groupAccountName;
    }


    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    class Column {
        private String name;
    }
}
