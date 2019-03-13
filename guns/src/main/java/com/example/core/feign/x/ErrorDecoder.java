package com.example.core.feign.x;

/**
 * @author DINGJUN
 * @date 2019.03.13
 */
public interface ErrorDecoder {
    Exception decode(String methodKey, Response response);
}
