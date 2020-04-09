package com.example.progress;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程执行中心
 */
@Component
public class ProgressCenter {

    // 状态中心
    public static Map<String, ProgressStatus> progressMap = new HashMap<>();

    // 线程池
    public static final ExecutorService execute = Executors.newCachedThreadPool();

    public ProgressStatus execute(RunnableCallBack callBack, String pid, String name, boolean sync) {
        final ProgressStatus status = new ProgressStatus();
        ProgressStatus progressStatus = progressMap.get(status);
        if (progressStatus != null) {
            System.out.println("任务没有结束，任务执行中：" + progressStatus.getPname());
            return progressStatus;
        } else {
            progressMap.put(pid, status);
        }
        status.setPid(pid);
        status.setPname(name);
        Future<?> submit = execute.submit(new Runnable() {
            @Override
            public void run() {
                callBack.run(status);
            }
        });
        // 如果方法是同步执行的，那么这里通过返回的Future接口获取执行结果
        if (sync) {
            try {
                submit.get();
            } catch (InterruptedException e) {
                System.out.println(e);
            } catch (ExecutionException e) {
                System.out.println(e);
            }
        }
        return status;
    }
}
