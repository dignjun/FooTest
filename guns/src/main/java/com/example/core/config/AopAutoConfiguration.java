package com.example.core.config;

import com.example.core.exception.DefaultExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 保留类，如果控制器需要些aop在这里写
 *
 * @author DINGJUN
 * @date 2019.03.13
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AopAutoConfiguration {
    /**
     * 默认的异常拦截器
     */
    @Bean
    public DefaultExceptionHandler defaultControllerExceptionHandler() {
        return new DefaultExceptionHandler();
    }

}
