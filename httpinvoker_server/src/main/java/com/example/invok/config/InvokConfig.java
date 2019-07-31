package com.example.invok.config;

import com.example.invok.service.UserService;
import com.example.invok.service.iml.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.remoting.httpinvoker.SimpleHttpInvokerRequestExecutor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import java.util.Properties;

@Configuration
public class InvokConfig {

    @Bean("/getUser") // 1.服务端的接口暴露直接使用url："/getUser" 2.这其实是一个mvc的控制器
    public HttpInvokerServiceExporter httpInvokerServiceExporter(UserService userService){ // 注意，这里实现需要通过参数传递方式给出接口的实现，否则异常
        HttpInvokerServiceExporter httpInvokerServiceExporter = new HttpInvokerServiceExporter();
        httpInvokerServiceExporter.setService(userService);
        httpInvokerServiceExporter.setServiceInterface(UserService.class);
        return httpInvokerServiceExporter;
    }

//    @Bean // 其实也就是将一个地址映射到一个mvc的控制器上。
    public HandlerMapping handlerMapping() {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        Properties mappings = new Properties();
        mappings.setProperty("/server/getUser", "httpInvokerServiceExporter");
        mapping.setMappings(mappings);
        return mapping;
    }
}
