package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;
import java.util.UUID;

/**
 * 验证csrf token，重写，ajax请求的xhr对象的send方法，
 * 如果是特定的url请求，如，save，update，delete等
 * 拦截并请求一个csrf_token，然后放在此次请求之中，服务端
 * 进行验证请求是否有效。
 */
@Controller
public class CsrfTokenController {

    @RequestMapping("/extjs1")
    public String index(){

        return "index_extjs1";
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(HttpServletRequest request, HttpServletResponse response){
        String a = request.getParameter("a");
        System.out.println(a);
        String b = request.getParameter("b");
        System.out.println(b);
        String csrf_token = request.getParameter("csrf_token");
        System.out.println("parameter token:" + csrf_token);
        String csrf_token1 = request.getHeader("csrf_token");
        System.out.println("header token:" + csrf_token1);
        if(!StringUtils.isEmpty(csrf_token) || !StringUtils.isEmpty(csrf_token1)) {
            System.out.println("hello ajax, data saved");
            return "hello ajax, data saved";
        } else {
            System.out.println("no csrf_token");
            return "no csrf_token";
        }
    }

    @RequestMapping("/csrf_token")
    @ResponseBody
    public String getCSRFToken(){
        System.out.println("get csrf token");
        return UUID.randomUUID().toString();
    }
}
