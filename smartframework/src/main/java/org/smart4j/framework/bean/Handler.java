package org.smart4j.framework.bean;

import java.lang.reflect.Method;

/**
 * 封装action信息
 * Created by DINGJUN on 2018/5/5.
 */
public class Handler {

    /**
     * controller类
     */
    private Class<?> controllerClass;

    /**
     * action方法
     */
    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
