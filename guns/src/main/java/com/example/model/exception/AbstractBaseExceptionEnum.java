package com.example.model.exception;

/**
 * foo异常规范
 * @author DINGJUN
 * @date 2019.03.13
 */
public interface AbstractBaseExceptionEnum {

    /**
     * 获取异常错误码
     *
     * @return
     */
    Integer getCode();

    /**
     * 获取异常提示信息
     * @return
     */
    String getMessage();

}
