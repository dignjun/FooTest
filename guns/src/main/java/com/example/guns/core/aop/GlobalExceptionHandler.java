package com.example.guns.core.aop;

import com.example.core.reqres.response.ErrorResponseData;
import com.example.guns.core.log.LogManager;
import com.example.guns.core.log.factory.LogTaskFactory;
import com.example.guns.core.shiro.ShiroKit;
import com.example.model.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.example.core.util.HttpContext.getRequest;

/**
 * 全局的异常拦截器(拦截所有的控制器)(带有@RequestMapping注解的方法上都会拦截)
 * Advice为切面增强
 * @author DINGJUN
 * @date 2019.03.14
 */
@ControllerAdvice
@Order(-1)
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 拦截业务异常
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponseData bussiness(ServiceException e) {
        LogManager.me().executeLog(LogTaskFactory.exceptionLog(ShiroKit.getUser().getId(), e));
        getRequest().setAttribute("tip", e.getMessage());
        logger.error("业务异常:", e);
        return new ErrorResponseData(e.getCode(), e.getMessage());
    }
}































