package com.example.core.aop;

import com.example.core.reqres.request.RequestData;
import com.example.model.constants.AopSortConstants;
import com.example.tool.util.StrUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

/**
 * 对控制器调用过程中，提供一种RequestData便捷调用的aop
 */
@Aspect
@Order(AopSortConstants.REQUEST_DATA_AOP_SORT)
public class RequestDataAop {
    @Pointcut("execution(* *..controller.*.*(..))")
    public void cutService(){
    }

    @Around("cutService()")
    public Object sessionKit(ProceedingJoinPoint point) throws Throwable {
        Object[] params = point.getArgs();
        String result = StrUtil.EMPTY;
        // 如果方法中有参数并且参数中有RequestData对象，就临时保存RequestData到RequestDataHolder
        if(params != null && params.length > 0){
            if(params[0] instanceof RequestData){

            }

        }

        return result;
    }
}























