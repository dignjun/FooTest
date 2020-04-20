package com.example.thread_demo.chap3;

import java.util.ArrayList;

/**
 * Created by DINGJUN on 2020.04.17.
 *
 * 线程间的通信
 *
 * 不通过wait和notify方式进行线程间的通信耗费相当多的资源，
 * 通过循环不断地查询共同变量的内容进行操作（注意需要使用volatile关键字修饰，这样可以获取内存数据，而不是缓存数据）
 */
public class Demo1 {

    // 不使用wait和notify方式进行通讯，通过公共变量通讯
    public static volatile ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {

        Thread te = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    list.add(i);
                    System.out.println("添加了" + (i + 1) + "个元素");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (list.size() == 5) {
                            System.out.println("list已经有5个数据了，线程B退出");
                            throw new InterruptedException();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        te.start();
        t2.start();
    }
}


