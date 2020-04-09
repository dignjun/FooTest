package com.example.thread_threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

public class ThreadLocalNormalUsage {

    // 定义一个可变线程池
    final static ExecutorService executor = Executors.newCachedThreadPool();

    // 定义成员变量
    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 定义线程变量
    static ThreadLocal<SimpleDateFormat> format2 = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static void main(String[] args) {
        // 两个线程的测试
//        twoThread();
        // 是个线程的测试
//        tenThread();
        // 100个线程测试，通过线程池实现
//        thousandThread();
        /**
         * 以上都是通过在每一个线程中创建一个DateFormate实例来实现的，数据不存在变量的数据安全问题
         *
         * 但是如果线程使用的同一个变量，就会存在成员变量和线程安全问题
         * 多线程用同一个变量的测试：
         * 1.创建100个线程，每个线程一个编号i
         * 2.然后让每个线程格式化自己编号*1000秒的时间
         * 3.输出，发现存在相同的时间输出
         * 4.问题，每个线程的时间肯定是不一样的，但是时间相同，说明多线程时，SimpleDateFormat是不安全的
         *
         * 解决方式一：在格式化的时候加锁，同步执行。同步代码块方式
         * 解决方式二：通过ThreadLocal方式解决，把SimpleDateFormat声明为线程变量即可。
         *              同时重写初始化方法，这样每个线程获取到自己的变量（和线程中直接给出有点类似了）
         *
         */
        unSafeFormat();

    }

    public static void unSafeFormat() {
        for (int i=0;i<100;i++) {
            final int a= i;
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    formatDate(a);
                }
            });
        }
    }

    public static void formatDate(int i) {
//        synchronized (ThreadLocalNormalUsage.class) {
            System.out.println(format2.get().format(new Date(1000 * i)));
//        }
    }

    public static void thousandThread() {
        for (int i=0;i<100;i++) {
            final int a = i;
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    sprintDate(a);
                }
            });
        }
    }

    public static void tenThread() {
        for (int i = 0; i <10; i++) {
            final int a = i;
            new Thread() {
                @Override
                public void run() {
                    sprintDate(a);
                }
            }.start();
        }
    }

    public static void twoThread() {
        new Thread() {
            @Override
            public void run() {
                sprintDate(1);
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                sprintDate(2);
            }
        }.start();
    }

    public static void sprintDate(int index) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
            while (true) {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() +": "+index+","+ dateFormat.format(new Date()));
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
