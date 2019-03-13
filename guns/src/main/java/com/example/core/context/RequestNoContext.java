package com.example.core.context;

import com.example.core.util.HttpContext;
import com.example.core.util.ToolUtil;
import com.example.model.api.base.AbstractBaseRequest;
import com.example.model.constants.RosesConstants;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取当前请求的请求号，没有请求号则生成空串
 * @author DINGJUN
 * @date 2019.03.13
 */
@Slf4j
public class RequestNoContext {

    public static String getRequestNoByHttpHeader() {
        HttpServletRequest request = HttpContext.getRequest();

        if (request == null) {
            if (log.isDebugEnabled()) {
                log.info("获取请求号失败，当前不是http请求环境！");
            }
            return "";
        } else {
            String requestNo = request.getHeader(RosesConstants.REQUEST_NO_HEADER_NAME);
            if (ToolUtil.isEmpty(requestNo)) {
                return "";
            } else {
                return requestNo;
            }
        }
    }

    /**
     * 通过请求参数获取requestNo，参数必须是AbstractBaseRequest的子类
     */
    public static String getRequestNoByRequestParam(Object[] params) {

        if (params == null || params.length <= 0) {
            return "";
        } else {
            for (Object paramItem : params) {
                if (paramItem instanceof AbstractBaseRequest) {
                    AbstractBaseRequest abstractBaseRequest = (AbstractBaseRequest) paramItem;
                    return abstractBaseRequest.getRequestNo();
                }
            }
            return "";
        }
    }

}
