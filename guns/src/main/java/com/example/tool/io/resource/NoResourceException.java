package com.example.tool.io.resource;

import com.example.tool.exceptions.ExceptionUtil;
import com.example.tool.io.IORuntimeException;
import com.example.tool.util.StrUtil;

/**
 * 资源文件或资源不存在异常
 * Created by DINGJUN on 2019.03.11.
 */
public class NoResourceException extends IORuntimeException {
    private static final long serialVersionUID = -623254467603299129L;

    public NoResourceException(Throwable e) {
        super(ExceptionUtil.getMessage(e), e);
    }

    public NoResourceException(String message) {
        super(message);
    }

    public NoResourceException(String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params));
    }

    public NoResourceException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public NoResourceException(Throwable throwable, String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params), throwable);
    }

    /**
     * 导致这个异常的异常是否是指定类型的异常
     *
     * @param clazz 异常类
     * @return 是否为指定类型异常
     */
    public boolean causeInstanceOf(Class<? extends Throwable> clazz) {
        Throwable cause = this.getCause();
        if (null != cause && clazz.isInstance(cause)) {
            return true;
        }
        return false;
    }
}
