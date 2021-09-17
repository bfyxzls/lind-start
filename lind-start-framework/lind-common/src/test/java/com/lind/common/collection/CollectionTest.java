package com.lind.common.collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 集合操作
 */
public class CollectionTest {
    List<Student> students = new ArrayList<Student>();

    @Test
    public void test1(){
        List<Integer> list1=new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        List<Integer> list2=new ArrayList<>();
        list2.add(3);
        list2.add(4);
        list2.add(5);

        System.out.println("====求交集===");
        List<Integer> list=list1.stream().filter(t->list2.contains(t)).collect(Collectors.toList());
        list.stream().forEach(System.out::println);

        System.out.println("====求差集===");
        list=list1.stream().filter(t-> !list2.contains(t)).collect(Collectors.toList());
        list.stream().forEach(System.out::println);


        System.out.println("====求并集===");
        list.addAll(list1);
        list.addAll(list2);
        list=list.stream().distinct().collect(Collectors.toList());
        list.stream().forEach(System.out::println);
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
    void init() {
        students.add(new Student(10, "zzl", 90));
        students.add(new Student(30, "lr", 98));
        students.add(new Student(9, "zhz", 90));
    }

    @Test
    public void ListToMap() {


    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    class Column {
        private String name;
    }
}
