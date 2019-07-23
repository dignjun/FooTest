package com.example.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Scope("prototype")
public class ExampleController {

    private String value;

    /**
     * RestController返回string作为ResponseBody输出
     * @return
     */
    @RequestMapping("/abc")
    public String hello(){
        System.out.println("value:" + value);
        return "hello";
    }

    public void setValue(String value){
        this.value = value;
    }
}
