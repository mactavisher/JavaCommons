package cn.com.lynx.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程并发下的原子操作
 *
 * 原子类可以保证数据的原子性，但不能保证控制线程的执行和调度顺序
 * 
 * 如果再自增之后再打印,可能会出现重复数据，这个并不是原子操作的问题，
 * 
 * 是由于线程切换和调度的问题,
 * 
 * 正确的做法是每次自增之后将值取出来放在容器里，然后统一打印
 * 
 * CAS Compare and swap
 * 
 * @author Lynx
 * 
 * @since 2020/07/01
 *
 */
public class AtomicIntegerTest {

    /*共享数据*/
    static volatile AtomicInteger atomicInteger = new AtomicInteger(0);

    public void test() {
        /*创建四个工作线程*/
        TestAtomicIntegerThread t1 = new TestAtomicIntegerThread(atomicInteger);
        TestAtomicIntegerThread t2 = new TestAtomicIntegerThread(atomicInteger);
        TestAtomicIntegerThread t3 = new TestAtomicIntegerThread(atomicInteger);
        TestAtomicIntegerThread t4 = new TestAtomicIntegerThread(atomicInteger);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t1.getName() + ":");
        t1.printValue();
        System.out.println(t2.getName() + ":");
        t2.printValue();
        System.out.println(t3.getName() + ":");
        t3.printValue();
        System.out.println(t4.getName() + ":");
        t4.printValue();
    }

    public static void main(String[] args) {
        new AtomicIntegerTest().test();
    }

    private static final class TestAtomicIntegerThread extends Thread {
        private AtomicInteger data;
        List<Integer> list = new ArrayList<>();

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                // 自增操作
                list.add(data.incrementAndGet());
            }
        }

        public void printValue() {
            for (Integer integer : list) {
                System.out.println(integer);
            }
        }

        public TestAtomicIntegerThread(AtomicInteger data) {
            this.data = data;
        }
    }
}
