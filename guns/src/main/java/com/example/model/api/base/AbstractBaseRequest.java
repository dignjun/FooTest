package com.example.model.api.base;

import com.example.model.validator.BaseValidatingParam;
import lombok.Getter;
import lombok.Setter;

/**
 * 远程服务的参数的基类
 *
 * @author DINGJUN
 * @date 2019.03.13
 */
@Getter
@Setter
public abstract class AbstractBaseRequest implements BaseValidatingParam {
    /**
     * 唯一请求号
     */
    private String requestNo;

    /**
     * 业务节点id
     */
    private String spanId;
}
