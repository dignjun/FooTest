package com.example;

import com.example.progress.ProgressCenter;
import com.example.progress.ProgressStatus;
import com.example.progress.RunnableCallBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
//@RestController
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @RequestMapping("/index")
    public String index () {
        System.out.println("--首页访问--");
        return "index";
    }

    @RequestMapping("/progressTest")
    @ResponseBody
    public String progressTest() {
        progressCenter.execute(new RunnableCallBack() {
            @Override
            public void run(ProgressStatus status) {
                status.setMessage("业务代码开始执行");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                status.setMessage("业务代码执行完成");
            }
        }, "test", "测试", false);
        return "";
    }

    @Autowired
    private ProgressCenter progressCenter;
}
