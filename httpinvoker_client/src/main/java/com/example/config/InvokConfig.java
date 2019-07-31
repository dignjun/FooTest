package com.example.config;

import com.example.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

@Configuration
public class InvokConfig {
    @Bean
    public HttpInvokerProxyFactoryBean httpInvokerProxyFactoryBean(){
        HttpInvokerProxyFactoryBean httpInvokerProxyFactoryBean = new HttpInvokerProxyFactoryBean();
        httpInvokerProxyFactoryBean.setServiceUrl("http://localhost:9099/server/getUser");
        httpInvokerProxyFactoryBean.setServiceInterface(UserService.class);
        return httpInvokerProxyFactoryBean;
    }
}
