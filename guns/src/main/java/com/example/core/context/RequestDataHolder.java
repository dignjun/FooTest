package com.example.core.context;

import com.example.core.reqres.request.RequestData;

/**
 * 请求数据的临时容器
 * @author DINGJUN
 * @date 2019.03.13
 */
public class RequestDataHolder {

    private static ThreadLocal<RequestData> holder = new ThreadLocal<>();

    public static void put(RequestData s) {
        holder.set(s);
    }

    public static RequestData get() {
        return holder.get();
    }

    public static void remove() {
        holder.remove();
    }
}
