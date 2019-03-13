package com.example.core.config;

import com.example.core.util.SpringContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 默认的工具集
 *
 * @author DINGJUN
 * @date 2019.03.13
 */
@Configuration
public class UtilAutoConfiguration {
    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
}
