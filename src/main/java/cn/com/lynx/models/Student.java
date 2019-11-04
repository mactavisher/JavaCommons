package cn.com.lynx.models;

import java.util.Comparator;

public class Student implements Comparable<Student> {

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(int age){
        this.age = age;
    }

    @Override public String toString() {
        return "Student{" + "age=" + age + '}';
    }

    @Override public int compareTo(Student o) {
        return this.getAge()-o.getAge();
    }
}
