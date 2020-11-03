package cn.com.lynx.designpatterns;

import java.util.ArrayList;
import java.util.List;


/**
 * 观察查接口
 */
interface Observer {
    void receiveUpdate(Object Data);
}

/**
 * 被观察的类型，也就是发布主题的类
 */
final class Subject {
    /*持有的观察者*/
    List<Observer> observers = new ArrayList<>();

    /*发送数据更新*/
    Object data;

    /**
     * 添加观察者
     *
     * @param observer 添加的观察者对象
     */
    void addObservers(Observer observer) {
        observers.add(observer);
    }

    /**
     * 更新数据,需要发送给观察者
     */
    public void updateData() {
        data = new Object();
        notifyUpdate();
    }

    /**
     * 发送数据给观察者
     */
    public void notifyUpdate() {
        if (null != observers && !observers.isEmpty()) {
            for (Observer observer : observers) {
                observer.receiveUpdate(data);
            }
        }
    }
}

/**
 * 实现了observer接口自定义类
 */
final class MyObserver implements Observer {
    @Override
    public void receiveUpdate(Object data) {
        System.out.println(toString() + "received data update from Subject:" + data.toString());
    }
}

/**
 * 测试
 */
public class ObserverPatternDemo {
    public static void main(String[] args) {
        Subject subject = new Subject();
        for (int i = 0; i <= 100; i++) {
            subject.addObservers(new MyObserver());
        }
        subject.updateData();
    }
}