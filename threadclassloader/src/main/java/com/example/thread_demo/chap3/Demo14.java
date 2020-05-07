package com.example.thread_demo.chap3;

/**
 * Created by DINGJUN on 2020.05.07.
 *
 * join方法的使用
 *
 * 方法join的作用就是使所属的线程对象x（子线程，也就是调用它的线程）执行run方法中的任务，而使得当前线程z（主线程）进行无限期的阻塞，
 * 等待线程x销毁后再继续线程z后面的代码。
 *
 * 方法join具有线程排队运行的作用，有些类似于同步代码块的运行效果
 *
 */
public class Demo14 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int value = (int) (Math.random() * 10000);
                System.out.println("等待" + value + "毫秒");
                try {
                    Thread.sleep(value);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.join();

        System.out.println("子线程执行后在执行");

        System.out.println("--------------------");

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t1.start();
                    t1.join();
                    System.out.println("t2在t1之后执行");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t2.start();
                    t2.join();
                    System.out.println("t3在t2之后执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t3.start();
        t3.join();

        System.out.println("主线程在t3之后执行");

    }
}
