package com.example.base_4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 线程的优先级
 * <p>
 * Created by DINGJUN on 2019.09.08.
 */
public class Priority {
    private static volatile boolean notStart = true;
    private static volatile boolean notEnd = true;

    public static void main(String[] args) throws InterruptedException {
        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            Job job = new Job(priority);
            jobs.add(job);
            Thread thread = new Thread(job, "Thread: " + i);
            thread.setPriority(priority);
            thread.start();
        }
        notStart = false;
        TimeUnit.SECONDS.sleep(10);
        notEnd = false;
        for (Job job : jobs) {
            System.out.println("job priority: " + job.priority + ", count: " + job.jobCount);
        }
        /**
         job priority: 1, count: 60113979
         job priority: 1, count: 95557243
         job priority: 1, count: 95106080
         job priority: 1, count: 94990165
         job priority: 1, count: 95381534
         job priority: 10, count: 70048991
         job priority: 10, count: 95562625
         job priority: 10, count: 95310741
         job priority: 10, count: 95946571
         job priority: 10, count: 47199574
         */
    }

    /**
     * 构建所有的线程, notStart之前都在等待, 构造完所有线程之后, 将notStart变为false,所有线程同时竞争开始运行
     * 按理说, 优先级高的线程应该早运行的,也就是计数应该更高的,但是看到运行的结果, java优先级并不能保证程序结果的正确性.
     *
     */
    static class Job implements Runnable { // 工作线程, 实现了Runnable接口, 优先级和任务数量的成员

        private int priority;
        private long jobCount;

        public Job(int priority) {
            this.priority = priority;
        }

        @Override
        public void run() {
            while (notStart) {
                Thread.yield(); // 让线程进入到就绪状态.
            }
            while (notEnd) {
                Thread.yield();
                jobCount++;
            }
        }
    }
}
