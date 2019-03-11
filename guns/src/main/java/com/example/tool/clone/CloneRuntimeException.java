package com.example.tool.clone;

import com.example.tool.exceptions.ExceptionUtil;
import com.example.tool.util.StrUtil;

/**
 * 克隆异常
 */
public class CloneRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 6774837422188798989L;

    public CloneRuntimeException(Throwable e) {
        super(ExceptionUtil.getMessage(e), e);
    }

    public CloneRuntimeException(String message) {
        super(message);
    }

    public CloneRuntimeException(String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params));
    }

    public CloneRuntimeException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CloneRuntimeException(Throwable throwable, String messageTemplate, Object... params) {
        super(StrUtil.format(messageTemplate, params), throwable);
    }

}
