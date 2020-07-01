package cn.com.lynx.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    static volatile AtomicInteger atomicInteger = new AtomicInteger(0);

    public void test() {
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
        t1.printValue();
        t2.printValue();
        t3.printValue();
        t4.printValue();
    }

    public static void main(String[] args) {
        new AtomicIntegerTest().test();
    }

    class TestAtomicIntegerThread extends Thread {
        private volatile AtomicInteger data;
        List<Integer> list = new ArrayList<>();

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                final int oldValue = data.get();
                list.add(oldValue);
                data.getAndIncrement();
            }
        }

        public void printValue() {
            for (Integer integer : list) {
                System.out.println(Thread.currentThread().getName() + ":" + integer);
            }
        }

        public TestAtomicIntegerThread(AtomicInteger data) {
            this.data = data;
        }
    }
}
