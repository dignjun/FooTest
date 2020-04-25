package com.example.thread_demo.chap3;


import java.util.ArrayList;
import java.util.List;

/**
 * 一个生产者和多个消费者
 *
 * 注意：1.此时需要使用notifyAll，因为就一个生产者，多个消费者执行概率大，可以出现假死的状态
 *      2.另外消费者多，可能消费同一个值，所以在等待的判断使用while，即使被唤醒也要再次判断集合是否有值，否则会出现异常
 */
public class Demo12 {
    public static void main(String[] args) {
        Demo12Service service = new Demo12Service();
        // 一个生产者
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    service.push(Math.random() + "");
                }
            }
        }).start();

        // 两个消费者
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        service.pop();
                    }
                }
            }).start();
        }
    }
}

class Demo12Service {
    private List<String> list = new ArrayList<>();

    synchronized public void push(String val) {
        try {
            if (list.size() == 1) {
                System.out.println(Thread.currentThread().getName() + "等待中");
                this.wait();
            }
            System.out.println(Thread.currentThread().getName() + ": 添加数据 " + val);
            System.out.println(Thread.currentThread().getName() + "还有" + list.size() + "个数据");
            list.add(val);
            this.notify();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    synchronized public void pop() {
        try {
            /*if*/while (list.size() == 0) { // 多个消费者的时候唤醒之后需要再次判断是否含有元素，否则多个消费者可能消费空的集合
                System.out.println(Thread.currentThread().getName() + "等待中");
                this.wait();
            }
            System.out.println(Thread.currentThread().getName() + "消费数据：" + list.get(0));
            System.out.println(Thread.currentThread().getName() + "还有" + list.size() + "个数据");
            list.remove(0);
//            this.notify(); // 多个消费者可能唤醒同样是消费者，导致假死
            this.notifyAll();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
