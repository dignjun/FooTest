package com.example.guns.core.interceptor;

import com.example.core.base.controller.BaseController;
import com.example.core.util.HttpSessionContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 静态调用session的拦截器
 *
 * @author DINGJUN
 * @date 2019.03.19
 */
@Aspect
@Component
public class SessionHolderInterceptor extends BaseController {
    @Pointcut("execution(* com.example.guns.*..controller.*.*(..))")
    public void cutService() {
    }

    @Around("cutService()")
    public Object sessionKit(ProceedingJoinPoint point) throws Throwable {
        HttpSessionContext.put(super.getHttpServletRequest().getSession());
        try {
            return point.proceed();
        } finally {
            HttpSessionContext.remove();
        }
    }

}




























