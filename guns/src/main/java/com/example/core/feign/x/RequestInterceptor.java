package com.example.core.feign.x;

/**
 * @author DINGJUN
 * @date 2019.03.13
 */
public interface RequestInterceptor {
    void apply(RequestTemplate requestTemplate);
}
