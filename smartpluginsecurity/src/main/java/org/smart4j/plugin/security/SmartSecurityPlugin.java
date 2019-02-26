package org.smart4j.plugin.security;

import org.apache.shiro.web.env.EnvironmentLoaderListener;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * smart security 插件
 * Created by DINGJUN on 2018/5/19.
 */
public class SmartSecurityPlugin implements ServletContainerInitializer{

    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        // 设置初始化参数
        servletContext.setInitParameter("", "");
        // 注册listener
        servletContext.addListener(EnvironmentLoaderListener.class);
        FilterRegistration.Dynamic smartSecurityFilter = servletContext.addFilter("", );
    }
}
