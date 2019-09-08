package com.example.async;

import org.junit.Test;

import java.util.Scanner;

/**
 * 方法的异步调用，回调测试
 */
public class CallBackTest {
    @Test
    public void test1() throws InterruptedException {
        Server server = new Server();
        Client client = new Client(server);

        client.sendMessage("abcd");

        Thread.sleep(10000);
    }

    @Test
    public void test2() throws InterruptedException {
        B b = new B();
        A a = new A(b);
        a.a_method("hello async method invoke");
    }

    /**
     *
     * A持有B,并在方法调用中调用了B的方法
     * 不过此时B的方法返回并不是立即返回的,也就是一个异步的调用过程,
     * 那么问题就是,如何在方法的接收到方法B的调用状态,成功或者失败?
     * 回调模式:通过方法传参一个接口实现
     */
    public class A {
        private B b;
        public A(B b) {
            this.b = b;
        }
        public void a_method(String str) throws InterruptedException {
            System.out.println("method a execute");
            b.b_method(str, new CallBack() {
                @Override
                public void precess(String str) {
                    // 这里为a方法调用需要b方法异步结果的处理逻辑
                    System.out.println("a method get b method async result, and get Str: " + str);
                }
            });
            System.out.println("method a execute over");
            Thread.sleep(5000L); // 这里的睡眠是因为单元测试一旦方法测试结束, jvm退出,所有的线程都退出了,b中的新启动的线程未运行就结束了
        }
    }

    public class B {
        public void b_method(String str, CallBack callBack) {
            System.out.println("method b execute");
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("method b async execute");
                    try {
                        Thread.sleep(3000L);    // 线程睡眠三秒,模式线程是异步执行的,并没有立即返回
                        System.out.println("method b async execute over");
                        if(true) {
                            callBack.precess(str);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            System.out.println(thread.isDaemon());
            System.out.println("method b execute over");

        }
    }

}
