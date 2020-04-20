package com.example.thread_demo.chap3;

/**
 * Created by DINGJUN on 2020.04.20.
 *
 * wait和notify需要获取到锁，所以必须要在同步的方法、代码块中使用
 *
 */
public class Demo2 {
    public static void main(String[] args) {
        String s = new String();

        // test1
        /*try {
            s.wait(); // java.lang.IllegalMonitorStateException
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        // test2
        /*synchronized (s) {
            System.out.println("同步代码块前");
            try {
                s.wait(); // 同一个对象只有wait没有notify，会一直等待，直到这个对象发出notify通知才会重新获取锁在执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("同步代码块后");
        }*/

        synchronized (s) {
            System.out.println("同步代码块前");
                s.notify(); // 同一个对象只有wait没有notify，会一直等待，直到这个对象发出notify通知才会重新获取锁在执行
            System.out.println("同步代码块后");
        }
    }
}
