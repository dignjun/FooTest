package com.example.core.util;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.example.core.converter.RequestDataMessageConvert;
import com.example.core.converter.RequestDataTypeMethodProcessor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 统一请求转化器默认配置
 *
 * @author DINGJUN
 * @date 2019.03.13
 */
public class MvcAdapter {
    public static RequestMappingHandlerAdapter requestMappingHandlerAdapter(
            RequestMappingHandlerAdapter original,
            FastJsonHttpMessageConverter fastJsonHttpMessageConverter,
            RequestDataMessageConvert requestDataMessageConvert) {
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(requestDataMessageConvert);

        List<HandlerMethodArgumentResolver> argumentResolvers = new ArrayList<>();
        argumentResolvers.add(new RequestDataTypeMethodProcessor(converters));
        original.setCustomArgumentResolvers(argumentResolvers);

        List<HttpMessageConverter<?>> list = new LinkedList<>();

        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setWriteAcceptCharset(false);
        list.add(stringHttpMessageConverter);

        list.add(fastJsonHttpMessageConverter);
        original.setMessageConverters(list);
        return original;
    }
}



























