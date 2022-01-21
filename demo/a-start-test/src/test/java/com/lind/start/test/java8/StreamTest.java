package com.lind.start.test.java8;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamTest {
    static Logger logger = LoggerFactory.getLogger(StreamTest.class);
    List<Test001> list;

    @Before
    public void init() {
        list = ImmutableList.of(
                new Test001("zzl", "01", 1),
                new Test001("lind", "02", 3),
                new Test001("zzl", "01", 3));
    }

    @Test
    public void filterTest() {
        list.stream().filter(o -> o.code.equals("01"))
                .map(o -> o.toString())
                .forEach(logger::info);
    }

    @Test
    public void mapTest() {
        list.stream().filter(o -> o.code.equals("02"))
                .map(o -> o.getName())
                .forEach(logger::info);
    }

    @Test
    public void sortTest() {
        list.stream().sorted(Comparator.comparing(Test001::getCode))
                .map(o -> o.toString())
                .forEach(logger::info);
    }

    @Test
    public void aggregateTest() {
        Integer result = list.stream().sorted(Comparator.comparing(Test001::getCode))
                .map(Test001::getScore).reduce((a, b) -> a + b).orElse(0);
        logger.info("a+b={}", result);
    }

    @Test
    public void groupTest() {
        Map<String, List<Test001>> collect = list.stream().collect(
                Collectors.groupingBy(
                        Test001::getCode
                ));
        System.out.println(collect);

        // 分组计数
        Map<String, Long> result2 = list.stream().collect(
                Collectors.groupingBy(
                        Test001::getCode, Collectors.counting()
                )
        );
        System.out.println(result2);

        // 多字段,分组计数
        Map<String, Long> result3= list.stream().collect(
                Collectors.groupingBy(o->
                        o.getCode()+"_"+o.getName(), Collectors.counting()
                )
        );
        System.out.println(result3);

    }

    @Data
    @AllArgsConstructor
    @ToString
    class Test001 {
        private String name;
        private String code;
        private Integer score;
    }

}
