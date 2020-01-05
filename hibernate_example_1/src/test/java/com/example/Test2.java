package com.example;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by DINGJUN on 2019.12.16.
 */
public class Test2 {
    @Test
    public void test1 () {
        // 创建一个threadlocal对象，并设置值
        ThreadLocal<String> threadLocal = new ThreadLocal<String>();
        threadLocal.set("test1");
        System.out.println(threadLocal.get());

        // 再次创建一个ThreadLocal，又是一个新的对象，获取不到之前放进线程变量里面的数据
        ThreadLocal<String> tl1 = new ThreadLocal<String>();
        System.out.println(tl1.get());
    }

    @Test
    public void test2 () {
        // 创建多个线程，每个线程在同一个线程变量里面放入不同的值，然后再获取这个值测试
        final ThreadLocal<String> threadLocal = new ThreadLocal<String>();
        List<Thread> threads = new LinkedList<Thread>();
        for (int i = 5; i > 0; i--) {
            final int temp = i;
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    threadLocal.set(""+temp);
                    System.out.println(threadLocal.get());
                }
            });
            threads.add(thread);
        }
        for (int i = 0;i < 5; i++) {
            threads.get(i).start();
        }
    }
}
