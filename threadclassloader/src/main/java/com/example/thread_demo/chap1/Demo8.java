package com.example.thread_demo.chap1;

/**
 * Created by DINGJUN on 2020.04.13.
 *
 * api - isAlive
 *
 * 运行前为false
 * 运行后为true
 *
 * api - sleep 当前正在执行的线程暂停执行
 *     - getId 获取当前线程的唯一标识
 */
public class Demo8 {
    public static void main(String[] args) {
        Demo8Thread t = new Demo8Thread();
        System.out.println("before running state: " + t.isAlive());
        t.start();
        System.out.println("after running state: " + t.isAlive());
    }
}

class Demo8Thread extends Thread {
    @Override
    public void run() {
        System.out.println("running state:" + this.isAlive());
    }
}
