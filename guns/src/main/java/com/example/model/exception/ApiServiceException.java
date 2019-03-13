package com.example.model.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 远程接口调用出现的业务异常
 *
 * @author DINGJUN
 * @date 2019.03.13
 */
@Getter
@Setter
public abstract class ApiServiceException extends RuntimeException {
    /**
     * 错误编码
     */
    private Integer code;

    /**
     * 错误的提示信息
     */
    private String errorMessage;

    public ApiServiceException(AbstractBaseExceptionEnum exception) {
        super(exception.getMessage());
        this.code = exception.getCode();
        this.errorMessage = exception.getMessage();
    }

    /**
     * 获取异常的类的具体名称
     */
    public abstract String getExceptionClassName();

}
