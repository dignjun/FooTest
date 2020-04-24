package com.example.thread_demo.chap3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 一个生产者和一个消费者操作集合
 *
 * 注意：同步方法的锁是当前实例，所以在方法中通过this.wait和this.notify等待和唤醒线程
 */
public class Demo11 {
    public static void main(String[] args) throws IOException {
        Demo11Service service = new Demo11Service();

        // 生产者
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    service.push(Math.random() + "");
                }
            }
        }).start();

        // 消费者
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String pop = service.pop();
                }
            }
        }).start();
    }
}

class Demo11Service {
    public static List<String> list = new ArrayList<>();

    // 有元素就等待
    synchronized public void push(String val) {
        try {
            if (list.size() == 1) {
                System.out.println(Thread.currentThread().getName() + "等待中...");
                this.wait(); // 同步方法是当前对象，所以使用this.wait方法锁定
            }
            list.add(val);
            System.out.println(Thread.currentThread().getName() + "添加数据: " + val);
            System.out.println(Thread.currentThread().getName() + ": 还有" + list.size() + "个数据");
            this.notify();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    synchronized public String pop() {
        String val = null;
        try {
            if (list.size() == 0) {
                System.out.println(Thread.currentThread().getName() + "等待中...");
                this.wait();
            }
            val = list.get(0);
            System.out.println(Thread.currentThread().getName() + "消费数据:" + val);
            System.out.println(Thread.currentThread().getName() + ": 还有" + list.size() + "个数据");
            list.remove(0);
            this.notify();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return val;
    }
}
