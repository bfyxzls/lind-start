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

/**
 * 集合操作
 */
public class CollectionTest {
    List<Student> students = new ArrayList<Student>();

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
