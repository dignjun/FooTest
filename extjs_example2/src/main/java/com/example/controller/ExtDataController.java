package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * extjs前端交互用Controller
 * 地址：
 *
 */
@Controller
//@RequestMapping("/extjs")
public class ExtDataController {


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){//首页
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){//登录页面
//        String password = "admin";
//        String username = "admin";
//        if(!password.equals("admin") && !username.equals("admin")){
//            return "login";
//        }
        return "login";
    }
}
