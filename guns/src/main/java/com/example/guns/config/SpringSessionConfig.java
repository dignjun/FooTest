package com.example.guns.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * spring session 配置
 * @author DINGJUN
 * @date 2019.03.14
 */
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)  //session过期时间  如果部署多机环境,需要打开注释
@ConditionalOnProperty(prefix = "guns", name = "spring-session-open", havingValue = "true")
public class SpringSessionConfig {

}
