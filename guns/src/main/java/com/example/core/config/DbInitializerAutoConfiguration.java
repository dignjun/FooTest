package com.example.core.config;

import com.example.core.db.listener.InitTableListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author DINGJUN
 * @date 2019.03.13
 */
@Configuration
public class DbInitializerAutoConfiguration {

    @Bean
    public InitTableListener initTableListener() {
        return new InitTableListener();
    }
}
