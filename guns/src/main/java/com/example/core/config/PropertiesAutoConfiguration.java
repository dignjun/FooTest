package com.example.core.config;

import com.example.core.config.properties.AppNameProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author DINGJUN
 * @date 2019.03.13
 */
@Configuration
@PropertySource("classpath:/default-config.properties")
public class PropertiesAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.application.name")
    public AppNameProperties appNameProperties() {
        return new AppNameProperties();
    }

}
