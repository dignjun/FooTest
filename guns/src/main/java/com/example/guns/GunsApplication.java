package com.example.guns;

import com.example.core.config.WebAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * springboot启动类
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.example", excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.example.core.config.WebAutoConfiguration"))
public class GunsApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(GunsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GunsApplication.class, args);
        // getName：为全限定名 getSimpleName：类名
        LOGGER.info(GunsApplication.class.getName() + " started is success!");
        LOGGER.info(GunsApplication.class.getSimpleName() + " started is success!");
    }
}
