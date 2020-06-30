package cn.com.lynx.designpatterns;

/**
 * singleton test dome
 *
 * @author lynx
 */
public class Singleton {
    public static void main(String[] args) {
        Thread t1 = new TestThread();
        Thread t2 = new TestThread();
        Thread t3 = new TestThread();
        Thread t4 = new TestThread();
        Thread t5 = new TestThread();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

    /**
     * 内部测试线程
     */
    static class TestThread extends Thread {
        @Override
        public void run() {
            System.out
                .println(Thread.currentThread().getName() + ":" + SomeDataType.getInstanceSynchronized().hashCode());
        }
    }
}

/**
 * 用来测试的实例数据类型
 */
class SomeDataType {
    private String name;
    private String age;
    private static volatile SomeDataType instance;

    // 隐藏共有构造函数
    private SomeDataType() {}

    /**
     * 然后使用本类作为锁来锁定初始化代码
     * 
     * volatile 保证可见性和禁止指令重排序,并不能保证原子性即也不能保证线程安全
     * 
     * 然后使用本类作为锁锁住初始化单例实例的代码块
     *
     * @return instance
     */
    @SuppressWarnings("unused")
    public static SomeDataType getInstanceThreadSafeDoubleCheck() {
        if (null != instance) {
            return instance;
        } else {
            synchronized (SomeDataType.class) {
                if (null == instance) {
                    instance = new SomeDataType();
                }
                return instance;
            }
        }
    }

    /**
     * 直接锁定方法，效率较低
     *
     * @return instance
     */
    @SuppressWarnings("unused")
    public static synchronized SomeDataType getInstanceSynchronized() {
        if (null == instance) {
            instance = new SomeDataType();
        }
        return instance;
    }

    /**
     * 线程不安全
     *
     * @return instance
     */
    @SuppressWarnings("unused")
    public static SomeDataType getInstanceCommon() {
        // not thread safe type
        if (null == instance) {
            instance = new SomeDataType();
        }
        return instance;
    }

    @SuppressWarnings("unused")
    public String getName() {
        return name;
    }

    @SuppressWarnings("unused")
    public void setName(String name) {
        this.name = name;
    }

    @SuppressWarnings("unused")
    public String getAge() {
        return age;
    }

    @SuppressWarnings("unused")
    public void setAge(String age) {
        this.age = age;
    }
}