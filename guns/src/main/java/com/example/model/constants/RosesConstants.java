package com.example.model.constants;

/**
 * 框架通用常量
 * @author DINGJUN
 * @date 2019.03.13
 */
public interface RosesConstants {
    /**
     * 请求号header标识
     */
    String REQUEST_NO_HEADER_NAME = "Request-No";

    /**
     * header中的spanId，传递规则：request header中传递本服务的id
     */
    String SPAN_ID_HEADER_NAME = "Span-Id";
}
