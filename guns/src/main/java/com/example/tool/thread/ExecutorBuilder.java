package com.example.tool.thread;

import com.example.tool.builder.Builder;
import com.sun.deploy.panel.ITreeNode;

import java.security.KeyPair;
import java.util.concurrent.*;

/**
 * {@link java.util.concurrent.ThreadPoolExecutor} 建造者
 * @author DINGJUN
 * @date 2019.03.12
 */
public class ExecutorBuilder implements Builder<ThreadPoolExecutor> {

    private int corePoolSize;
    private int maxPoolSize = Integer.MAX_VALUE;
    private long keepAliveTime = TimeUnit.SECONDS.toNanos(60);
    private BlockingQueue<Runnable> workQueue;
    private ThreadFactory threadFactory;
    private RejectedExecutionHandler handler;

    /**
     * 设置初始池大小，默认0
     *
     * @param corePoolSize 初始池大小
     * @return this
     */
    public ExecutorBuilder setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
        return this;
    }

    /**
     * 设置最大池大小（允许同时执行的最大线程数）
     *
     * @param maxPoolSize 最大池大小
     * @return
     */
    public ExecutorBuilder setMaxPoolSize(int maxPoolSize){
        this.maxPoolSize = maxPoolSize;
        return this;
    }

    /**
     * 设置线程存活时间，即当池中线程多于初始大小时，多出的线程保留的时长
     * @param keepAliveTime 线程存活时间
     * @param unit 单位
     * @return this
     */
    public ExecutorBuilder setKeepAliveTime(long keepAliveTime, TimeUnit unit){
        return setKeepAliveTime(unit.toNanos(keepAliveTime));
    }

    /**
     * 设置线程存活时间，即当线程池中线程多于初始大小时，多出的线程保留的时长，单位纳秒
     * @param keepAliveTime 线程存活时间，单位纳秒
     * @return
     */
    public ExecutorBuilder setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
        return this;
    }

    /**
     * 设置队列，用于存放未执行的线程
     * 可选的队列有：
     * 1.SynchronousQueue       它将任务直接提交给线程而不是保持它们，当运行线程小于maxPoolSize时会创建新的线程
     * 2.LinkedBlockingQueue    无界队列，当运行线程大于corePoolSize时始终放入此队列，此时maximumPoolSize无效
     * 3.ArrayBlockingQueue     有界队列，但对无界队列有利于控制队列大小，队列满时，运行线程小于maxPoolSize时会创建线程,否则触发异常策略
     * @param workQueue 队列
     * @return
     */
    public ExecutorBuilder setWorkQueue(BlockingQueue<Runnable> workQueue){
        this.workQueue = workQueue;
        return this;
    }

    /**
     * 使用{@link SynchronousQueue}作为等待队列
     * @return
     */
    public ExecutorBuilder useSynchronousQueue(){
        return setWorkQueue(new SynchronousQueue<Runnable>());
    }

    /**
     * 设置线程工厂，用于自定义线程创建
     *
     * @param threadFactory 线程工厂
     * @return this
     */
    public ExecutorBuilder setThreadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
        return this;
    }

    /**
     * 设置当线程阻塞（block）时的异常处理器，所谓线程阻塞即线程池和等待队列已满，无法处理线程时采取的策略
     * 此处可以使用JDK预定的集中策略，见{@link RejectPolicy}
     * @param handler
     * @return
     */
    public ExecutorBuilder setHandler(RejectedExecutionHandler handler){
        this.handler = handler;
        return this;
    }

    /**
     * 创建ExecutorBuilder，开始构建
     * @return {@link ExecutorBuilder}
     */
    public static ExecutorBuilder create(){
        return new ExecutorBuilder();
    }

    /**
     * 构建ThreadPoolExecutor
     * @return
     */
    @Override
    public ThreadPoolExecutor build() {
        return build(this);
    }

    /**
     * 构建ThreadPoolExecutor
     * @param builder {@link ExecutorBuilder}
     * @return {@link ThreadPoolExecutor}
     */
    public static ThreadPoolExecutor build(ExecutorBuilder builder){
        final int corePoolSize = builder.corePoolSize;
        final int maxPoolSize = builder.maxPoolSize;
        final long keepAliveTime = builder.keepAliveTime;
        final BlockingQueue<Runnable> workQueue;
        if(null != builder.workQueue) {
            workQueue = builder.workQueue;
        } else {
            //corePoolSize为0则要使用SynchronousQueue避免无限阻塞
            workQueue = (corePoolSize <= 0) ? new SynchronousQueue<Runnable>() : new LinkedBlockingDeque<Runnable>();
        }
        final ThreadFactory threadFactory = (null != builder.threadFactory) ? builder.threadFactory:Executors.defaultThreadFactory();
        final RejectedExecutionHandler handler = builder.handler;
        if(null == handler) {
            return new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.NANOSECONDS, workQueue, threadFactory);
        } else {
            return new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.NANOSECONDS, workQueue, threadFactory, handler);
        }
    }
}



















