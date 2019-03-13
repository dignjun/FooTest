package com.example.core.converter;

import com.example.core.reqres.request.RequestData;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.List;

/**
 * 请求转化器
 *
 * @author DINGJUN
 * @date 2019.03.13
 */
public class RequestDataTypeMethodProcessor extends RequestResponseBodyMethodProcessor {
    public RequestDataTypeMethodProcessor(List<HttpMessageConverter<?>> converters) {
        super(converters);
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(RequestData.class);
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return false;
    }
}
