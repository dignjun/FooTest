package com.example.core.base.controller;

import com.alibaba.fastjson.JSON;
import com.example.core.reqres.response.ErrorResponseData;
import com.example.model.exception.enums.CoreExceptionEnum;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 错误页面的默认跳转(例如请求404的时候,默认走这个视图解析器)
 * @author DINGJUN
 * @date 2019.03.13
 */
public class GlobalErrorView implements View {

    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public void render(Map<String, ?> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json");

        if (map != null && map.get("code") != null && map.get("message") != null) {
            httpServletResponse.getWriter().write(JSON.toJSONString(new ErrorResponseData((Integer) map.get("code"), (String) map.get("message"))));
        } else {
            if (map != null && map.get("status") != null && map.get("error") != null) {
                Object status = map.get("status");
                Object error = map.get("error");
                httpServletResponse.getWriter().write(JSON.toJSONString(new ErrorResponseData((Integer) status, (String) error)));
            } else {
                httpServletResponse.getWriter().write(JSON.toJSONString(new ErrorResponseData(CoreExceptionEnum.PAGE_NULL.getCode(), CoreExceptionEnum.PAGE_NULL.getMessage())));
            }
        }
    }

}
