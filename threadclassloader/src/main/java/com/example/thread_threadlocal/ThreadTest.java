package com.example.thread_threadlocal;

import java.util.HashMap;

/**
 * 线程变量的测试 ThreadLocal
 *
 * 1. ThreadLocal变量的initialValue方法只有线程在第一次get的时候进行调用，
 *      或者线程执行了remove方法会再次调用这个初始化方法
 *
 * 2. 线程变量的局部特性依赖于每次调用get方法的对象初始化，如果是直接给一个对象的引用，
 *      如果对象属性发生了改变，那么线程变量持有的对象引用一定也是发生改变的
 *
 * 3. 线程变量的在线程中的修改会反映到对象属性上（因为引用关联）
 *      同样，对象属性的修改（某一个线程修改）也会反映到线程变量的对象属性上（同样因为对象关联）
 *
 */
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        TestContext testContext = new TestContext();
        System.out.println("操作前：" + testContext.map.get("A"));
        Thread t1 = new Thread() {
            @Override
            public void run() {
                TestContext.local.set(testContext);
                TestContext context = TestContext.local.get();
                context.map.put("A", 123);
                Thread t2 = new Thread() {
                    @Override
                    public void run() {
                        TestContext.local.set(context);

                        System.out.println("B1 " + TestContext.local.get().map.get("A"));
                        try {
                            Thread.currentThread().sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("B2 " + TestContext.local.get().map.get("A"));

                    }
                };
                context.map.put("A", 456);
                t2.start();

            }
        };
        t1.start();
        Thread.sleep(1000);
        System.out.println("操作后：" +testContext.map.get("A"));
        testContext.map.put("A", 789);

    }
}

class TestContext {

    // 成员变量
    public static ThreadLocal<TestContext> local = new ThreadLocal<TestContext>(){
        @Override
        protected TestContext initialValue() {
            System.out.println("线程初始化被调用了");
            return new TestContext();
        }
    };

    // map变量
    public HashMap<String, Object> map = new HashMap<>();

//    public TestContext
}
