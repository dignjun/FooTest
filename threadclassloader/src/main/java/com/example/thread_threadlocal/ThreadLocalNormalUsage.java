package com.example.thread_threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

public class ThreadLocalNormalUsage {

    final static ExecutorService executor = Executors.newScheduledThreadPool(5, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            t.setPriority(Thread.NORM_PRIORITY - 1);
            return t;
        }
    });

    public static void main(String[] args) {
        twoThread();
        tenThread();

    }

    public static void thousandThread() {

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
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (true) {
                Thread.sleep(5000L);
                System.out.println("-"+index+"-"+ dateFormat.format(new Date()));
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
