package com.example.guns.core.interceptor;

import com.example.guns.core.shiro.ShiroKit;
import com.example.guns.core.shiro.ShiroUser;
import com.example.guns.core.util.DefaultImages;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自动渲染当前用户信息登录属性的过滤器
 * @author DINGJUN
 * @date 2019.03.19
 */
public class AttributeSetInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 没有视图的直接跳过过滤器
        if(modelAndView == null || modelAndView.getView() == null) {
            return ;
        }

        // 视图结尾不是html的直接跳过
        if(!modelAndView.getViewName().endsWith("html")){
            return ;
        }

        ShiroUser user = ShiroKit.getUser();
        if(user == null) {
            throw new AuthenticationException("当前没有登录帐号!");
        } else {
            modelAndView.addObject("name", user.getName());
            modelAndView.addObject("avatar", DefaultImages.defaultAvatarUrl());
            modelAndView.addObject("email",user.getEmail());
        }
    }
}







































