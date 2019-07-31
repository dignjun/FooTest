package com.example.controller;

import com.example.invok.bean.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/user")
    public String getUser(){
        User alen = userService.getUserbyName("linda");
        User lisa = userService.getUserByName2("lisa");
        return alen.toString() + " : " + lisa.toString();
    }
}
