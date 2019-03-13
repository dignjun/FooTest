package com.example.core.util;

import javax.servlet.http.HttpSession;

/**
 * 非Controller中获取当前session的工具类
 *
 * @author DINGJUN
 * @date 2019.03.13
 */
public class HttpSessionContext {
    private static ThreadLocal<HttpSession> tl = new ThreadLocal<>();

    public static void put(HttpSession s) {
        tl.set(s);
    }

    public static HttpSession get() {
        return tl.get();
    }

    public static void remove() {
        tl.remove();
    }
}



























