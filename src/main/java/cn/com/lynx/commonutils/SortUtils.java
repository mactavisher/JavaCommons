package cn.com.lynx.commonutils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @des: 常用的排序算法工具类
 * @Author: lynx
 * @Date @Date 2019/8/22 10:20
 */
public class SortUtils {

    // 隐藏构造函数，外部无法实例化
    private SortUtils() {}

    /**
     * 冒泡排序 针对自定义排序方法， 自定义实现了Comparator的比较器对集合元素经行排序
     *
     * @param list
     * @param comparator
     * @param <E>
     * @return 排序号的集合
     */
    public static <E> void bubbleSort(List<E> list, Comparator<? super E> comparator) {
        if (list != null && !list.isEmpty()) {
            E temp;
            final int size = list.size();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size - i; j++) {
                    if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                        temp = list.get(j);
                        list.set(j, list.get(j + 1));
                        list.set(j + 1, temp);
                    }
                }
            }
        }
    }

    /**
     * 冒泡排序 针对自定义排序方法， 针对实现了Comparable的接口的实体，对元素集合进行排序
     *
     * @param list
     * @param <E>
     * @return
     */
    public static <E extends Comparable<? super E>> void bubbleSort(List<E> list) {
        if (list != null && !list.isEmpty()) {
            E temp;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size - i; j++) {
                    if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                        temp = list.get(j);
                        list.set(j, list.get(j + 1));
                        list.set(j + 1, temp);
                    }
                }
            }
        }
    }

    /**
     * 选择排序 针对实现了Comparable的接口的实体进行排序
     *
     * @param list
     * @param <E>
     * @return
     */
    public static <E extends Comparable<? super E>> void selectSort(List<E> list) {
        if (list != null && !list.isEmpty()) {
            E temp;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                // 记录每一次循环最小值的位置
                int pos = i;
                for (int j = i + 1; j < size; j++) {
                    if (list.get(pos).compareTo(list.get(j)) > 0) {
                        pos = j;
                    }
                }
                // 最小的数与第i个位置的数交换
                temp = list.get(i);
                list.set(i, list.get(pos));
                list.set(pos, temp);
            }
        }
    }

    /**
     * 选择排序 自定义的排序对象对集合元素经行排序
     *
     * @param list
     * @param <E>
     * @return
     */
    public static <E> void selectSort(List<E> list, Comparator<? super E> comparator) {
        if (list != null && !list.isEmpty()) {
            E temp;
            final int size = list.size();
            for (int i = 0; i < size; i++) {
                // 记录每一次循环最小值的位置
                int pos = i;
                for (int j = i + 1; j < size; j++) {
                    if (comparator.compare(list.get(pos), list.get(j)) > 0) {
                        pos = j;
                    }
                }
                // 最小的数与第i个位置的数交换
                temp = list.get(i);
                list.set(i, list.get(pos));
                list.set(pos, temp);
            }
        }
    }

    /**
     * 插入排序 针对实现了Comparable的接口的实体进行排序
     *
     * @param list
     * @param <E>
     * @return
     */
    public static <E extends Comparable<? super E>> void insertSort(List<E> list) {
        if (list != null && !list.isEmpty()) {
            E temp;
            final int size = list.size();
            for (int i = 1; i < size; i++) {
                temp = list.get(i);
                for (int j = 0; j < i; j++) {
                    if (temp.compareTo(list.get(j)) < 0) {
                        for (int k = i; k > j; k--) {
                            list.set(k, list.get(k - 1));
                        }
                        list.set(j, temp);
                        break;
                    }
                }
            }
        }
    }

    /**
     * 插入排序
     *
     * @param list
     * @param comparator
     * @param <E>
     * @return
     */
    public static <E extends Comparable<? super E>> void insertSort(List<E> list, Comparator<? super E> comparator) {
        if (list != null && !list.isEmpty()) {
            E temp;
            final int size = list.size();
            for (int i = 1; i < size; i++) {
                temp = list.get(i);
                for (int j = 0; j < i; j++) {
                    if (comparator.compare(temp, list.get(j)) < 0) {
                        for (int k = i; k > j; k--) {
                            list.set(k, list.get(k - 1));
                        }
                        list.set(j, temp);
                        break;
                    }
                }
            }
        }
    }

    /**
     * 适用大数据量排序
     *
     * @param list
     * @param comparator
     * @param <E>
     * @return
     */
    public static <E> List<E> streamSort(List<E> list, Comparator<? super E> comparator) {
        if (list != null && !list.isEmpty()) {
            return list.stream().sorted(comparator).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {

        /**
         * 用来测试的内部类，隐藏对外部不可见
         */
        class Student implements Comparable<Student> {
            private int age;

            public Student(int age) {
                this.age = age;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            @Override
            public String toString() {
                return "Student{" + "age=" + age + '}';
            }

            @Override
            public int compareTo(Student o) {
                return this.getAge() - o.getAge();
            }

            @Override
            public boolean equals(Object obj) {
                return super.equals(obj);
            }

            @Override
            public int hashCode() {
                return super.hashCode();
            }
        }

        class StudentComparator implements Comparator<Student> {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge() - o2.getAge();
            }
        }

        /* testing
        List<Student> students = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            students.add(new Student(new Random().nextInt(1000000)));
        }
        students.forEach(student -> System.out.println("排序前学生年龄为：" + student.getAge() + "\n"));
        SortUtils.bubbleSort(students, new StudentComparator());
        SortUtils.bubbleSort(students);
        SortUtils.streamSort(students, new StudentComparator());
        SortUtils.insertSort(students);
        SortUtils.insertSort(students, new StudentComparator());
        SortUtils.selectSort(students);
        SortUtils.selectSort(students, new StudentComparator());
        students.forEach(student -> System.out.println("排序后学生年龄为：" + student.getAge() + "\n"));
        */
    }
}
