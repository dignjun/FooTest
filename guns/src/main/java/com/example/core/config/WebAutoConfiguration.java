package com.example.core.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.example.core.aop.RequestDataAop;
import com.example.core.base.controller.GlobalErrorView;
import com.example.core.converter.RequestDataMessageConvert;
import com.example.core.util.MvcAdapter;
import com.example.tool.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.util.Date;

/**
 * 默认web配置
 *
 * @author DINGJUN
 * @date 2019.03.13
 */
@Configuration
@ConditionalOnBean(ServletContext.class)
public class WebAutoConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    /**
     * 默认错误页面，返回json
     */
    @Bean("error")
    public GlobalErrorView error() {
        return new GlobalErrorView();
    }

    /**
     * 控制器层临时缓存RequestData的aop
     */
    @Bean
    public RequestDataAop requestDataAop() {
        return new RequestDataAop();
    }

    /**
     * RequestData解析器，fastjson的converter
     */
    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter(
            FastJsonHttpMessageConverter fastJsonHttpMessageConverter,
            RequestDataMessageConvert requestDataMessageConvert) {

        return MvcAdapter.requestMappingHandlerAdapter(
                super.requestMappingHandlerAdapter(), fastJsonHttpMessageConverter, requestDataMessageConvert);
    }

    /**
     * RequestData解析器
     */
    @Bean
    public RequestDataMessageConvert requestDataMessageConvert() {
        return new RequestDataMessageConvert();
    }

    /**
     * 时间转化器
     */
    @PostConstruct
    public void addConversionConfig() {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
        if ((initializer != null ? initializer.getConversionService() : null) != null) {
            GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
            genericConversionService.addConverter(new StringToDateConverter());
        }
    }

    public class StringToDateConverter implements Converter<String, Date> {
        @Override
        public Date convert(String dateString) {
            return DateUtil.parse(dateString);
        }
    }

}
