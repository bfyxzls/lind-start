package com.lind.common.collection;

import lombok.Data;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 基于优先级的队列
 * 优先队列不允许空值，而且不支持non-comparable（不可比较）的对象，自定义对象应该实现Comparator接口给对象排序
 * 优先队列的大小是不受限制的，但在创建时可以指定初始大小
 * PriorityQueue是非线程安全的，所以Java提供了PriorityBlockingQueue（实现BlockingQueue接口）用于Java多线程环境
 */
public class PriorityQueueTest {
    @SneakyThrows
    @Test
    public void test() {
        Queue<Customer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Customer(1, "zhansan"));
        priorityQueue.add(new Customer(2, "lisi"));
        priorityQueue.add(new Customer(4, "wangwu"));
        int i=0;
        while (true) {

            Customer cust = priorityQueue.poll();
            if (cust == null) break;
            System.out.println("Processing Customer =" + cust.toString());
            Thread.sleep(1000);
            if(i==0) {
                priorityQueue.add(new Customer(3, "back-door"));
                priorityQueue.add(new Customer(5, "final"));
            }
            i++;
        }

    }

    @Data
    static class Customer implements Comparable<Customer> {
        private int id;
        private String name;

        public Customer(int i, String n) {
            this.id = i;
            this.name = n;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(Customer o) {
            if (this.id < o.id) return -1;
            else if (this.id == o.id) return 0;
            else return 1;
        }
    }
}
