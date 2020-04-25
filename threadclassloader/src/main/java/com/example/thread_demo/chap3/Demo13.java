package com.example.thread_demo.chap3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DINGJUN on 2020.04.25.
 *
 * 多生产者和多消费者操作集合
 *
 * 1.使用notify会有【假死】状态产生
 * 2.多个消费者，所以在消费之前需要判断是否存在元素，也就是在唤醒的时候需要继续判断是否含有元素。使用while
 * 3.同理多个生产者，生产者唤醒生产之前需要再次判断是否含有元素，否则会添加多个元素。使用while
 *
 */
public class Demo13 {
    public static void main(String[] args) {
        Demo13Service service = new Demo13Service();
        for (int i = 0; i < 4; i ++) {
            // 生产者线程
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        service.push(Math.random() + "");
                    }
                }
            }).start();

            // 消费者线程
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

class Demo13Service {
    // 因为是成员变量，所以在操作的时需要时同步的代码块，否则可能出现数据不一致的情况
    private List<String> list = new ArrayList<>();

    // 基于成员的同步添加元素方法
    synchronized public void push(String val) {
        try {
//            if (list.size() == 1) {
            while (list.size() == 1) {
                System.out.println(Thread.currentThread().getName() + "等待中");
                this.wait();
            }
            System.out.println(Thread.currentThread().getName() + "添加元素" + val);
            list.add(val);
            System.out.println(Thread.currentThread().getName() + "还有" + list.size() + "个元素");
//            this.notify();
            this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 同步的删除元素方法
    synchronized public void pop() {
        try {
//            if (list.size() == 0) {
            while (list.size() == 0) {
                System.out.println(Thread.currentThread().getName() + "等待中");
                this.wait();
            }
            System.out.println(Thread.currentThread().getName() + "消费元素" + list.get(0));
            list.remove(0);
            System.out.println(Thread.currentThread().getName() + "还有" + list.size() + "个元素");
//            this.notify();
            this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
