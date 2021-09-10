package com.lind.common.collection;

import lombok.Data;

@Data
public class Student implements Comparable<Student> {
    private int age;
    private String name;
    private double score;

    public Student() {
    }

    public Student(int age, String name, double score) {
        this.age = age;
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    /**
     * 1表示大于，-1表示小于，0表示等于;-1的在前面，1的在后面，很好理解
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Student o) {
        if (this.score > o.score)
            return -1;
        else if (this.score < o.score)
            return 1;
        else {
            if (this.age < o.age)
                return -1;
            else if (this.age > o.age)
                return 1;
            else
                return 0;
        }
    }
}