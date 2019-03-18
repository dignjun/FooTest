package com.example.guns.config.web;

import com.example.guns.config.properties.BeetlProperties;
import com.example.guns.core.beetl.BeetlConfiguration;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * web配置类
 *
 * @author DINGJUN
 * @date 2019.03.18
 */
@Configuration
public class BeetlConfig {

    @Autowired
    public BeetlProperties beetlProperties;

    /**
     * beetl配置
     * @return
     */
    @Bean
    public BeetlConfiguration beetlConfiguration(){
        BeetlConfiguration beetlConfiguration = new BeetlConfiguration();
        beetlConfiguration.setResourceLoader(new ClasspathResourceLoader(BeetlConfig.class.getClassLoader(),beetlProperties.getPrefix()));
        beetlConfiguration.setConfigProperties(beetlProperties.getProperties());
        return beetlConfiguration;
    }

    /**
     * beetl的视图解析器
     * @return
     */
    @Bean
    public BeetlSpringViewResolver beetlViewResolver(){
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setConfig(beetlConfiguration());
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setOrder(0);
        return beetlSpringViewResolver;
    }
}






















