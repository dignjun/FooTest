package org.smart4j.framework.bean;

/**
 * 封装请求信息
 *
 * Created by DINGJUN on 2018/5/5.
 */
public class Request {
    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 请求路径
     */
    private String requestPath;

    public Request(String requestMethod, String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    // TODO hashcode and equals 方法没有重写

}
