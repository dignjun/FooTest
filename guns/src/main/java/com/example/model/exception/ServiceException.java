package com.example.model.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 业务异常类封装
 *
 * @author DINGJUN
 * @date 2019.03.13
 */
@Getter
@Setter
public class ServiceException extends RuntimeException{
    private Integer code;
    private String errorMessage;

    public ServiceException(Integer code, String errorMessage) {
        super(errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public ServiceException(AbstractBaseExceptionEnum exception){
        super(exception.getMessage());
        this.code = exception.getCode();
        this.errorMessage = exception.getMessage();
    }


}
