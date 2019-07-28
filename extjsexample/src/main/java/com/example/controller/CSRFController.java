package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class CSRFController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CSRFController.class);

    /**
     * 测试web服务是否正常，可以正常访问到jsp页面
     * 测试地址：http://localhost:9001/example/index
     * @param request
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        LOGGER.info("访问主页");
        String referer = request.getHeader("Referer");
        System.out.println(referer);
        for (Cookie cookie : request.getCookies()) {
            System.out.println(cookie.getName());
        }
        HttpSession session = request.getSession();
        System.out.println(session);
        return "index";
    }

}
