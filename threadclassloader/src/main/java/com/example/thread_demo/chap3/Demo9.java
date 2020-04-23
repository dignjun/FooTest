package com.example.thread_demo.chap3;

/**
 * Created by DINGJUN on 2020.04.23.
 *
 * 生产者消费者模式实现之一个生产者一个消费者
 *
 * 不过在此代码基础上构建多生产者和多消费者就会产生【假死】的状态，也就是所有的线程都是等待状态
 *
 * 产生问题的根本原因是：notify只唤醒一个等待线程。碰巧所有的等待线程都是等待状态，
 *                      此时唤醒了一个生产者，生产者生产完成之后会唤醒一个等待线程，如果此时又是生产者被唤醒
 *                      那么肯定也会进入等待状态，所有的线程就都是等待状态了。看Demo10实现测试。
 */
public class Demo9 {
    public static void main(String[] args) {
        Object lock = new Object();
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        synchronized (lock) {
                            if (!"".equals(Demo9VO.value)) { // 生产者判断有值就等待
                                lock.wait();
                            }
                            String value = System.currentTimeMillis() + "";
                            System.out.println("set value: " + value);
                            Demo9VO.value = value;
                            lock.notify(); // 没值就设置值，并且唤醒等待的消费者线程
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        synchronized (lock) {
                            if ("".equals(Demo9VO.value)) { // 消费者判断没值就等待
                                lock.wait();
                            }
                            System.out.println("get value: " + Demo9VO.value);
                            Demo9VO.value = "";
                            lock.notify(); // 有值就消费，并且通知生产者生产
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();
    }
}

class Demo9VO {
    public static String value = "";
}
