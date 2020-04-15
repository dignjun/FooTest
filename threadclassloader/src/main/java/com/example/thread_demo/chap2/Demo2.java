package com.example.thread_demo.chap2;

/**
 * Created by DINGJUN on 2020.04.15.
 * synchronized关键字放在方法上，锁是当前的对象，意味着一个方法访问的时候，其他带有【锁】的方法是不能并发访问的
 *              放在类或者静态方法上，锁是当前类的class对象
 *              放在代码块上，锁是括号中指定的锁对象
 */
public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
        // 加上同步关键字方法线程顺序执行
        Service2 s2 = new Service2();
        Thread21 thread21 = new Thread21(s2);
        Thread22 thread22 = new Thread22(s2);
        thread21.start();
        thread22.start();

        System.out.println("---------test one over-------");
        Thread.sleep(5000);
        // 如果是两个业务类，则是两把锁，那么此时是并发同步执行
        Service2 s22 = new Service2();
        Thread21 ss = new Thread21(s2);
        Thread22 ss2 = new Thread22(s22);
        ss.start();
        ss2.start();
    }
}

class Service2 {
    private int num;
    synchronized public void add(String username) {
        if ("a".equals(username)) {
            num =100;
            System.out.println("a is set");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            num =200;
            System.out.println("b is set");
        }
        System.out.println("username="+username+", num="+num);
    }
}

class Thread21 extends Thread{
    private Service2 service2;
    public Thread21(Service2 service2) {
        this.service2 = service2;
    }

    @Override
    public void run() {
        service2.add("a");
    }
}

class Thread22 extends Thread{
    private Service2 service2;
    public Thread22(Service2 service2) {
        this.service2 = service2;
    }

    @Override
    public void run() {
        service2.add("b");
    }
}