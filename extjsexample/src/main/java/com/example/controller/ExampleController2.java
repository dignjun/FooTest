package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExampleController2 {

    private String value;

    /**
     * 不使用RestController，使用Controller，字符串会作为视图名进行匹配，
     * 测试地址：http://localhost:9001/example/word
     * 报错，Whitelabel Error Page。因为没有找到这个视图。
     * @return
     */
    @RequestMapping("/world")
    public String hello() {
        return "index";
    }
}
