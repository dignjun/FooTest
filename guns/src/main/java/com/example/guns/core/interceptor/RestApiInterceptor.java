package com.example.guns.core.interceptor;

import com.example.core.reqres.response.ErrorResponseData;
import com.example.core.util.RenderUtil;
import com.example.guns.core.common.constant.JwtConstants;
import com.example.guns.core.common.exception.BizExceptionEnum;
import com.example.guns.core.util.JwtTokenUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Rest Api接口鉴权
 *
 * @author DINGJUN
 * @date 2019.03.19
 */
public class RestApiInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }
        return check(request, response);
    }

    private boolean check(HttpServletRequest request, HttpServletResponse response) {
        if (request.getServletPath().equals(JwtConstants.AUTH_PATH)) {
            return true;
        }
        final String requestHeader = request.getHeader(JwtConstants.AUTH_HEADER);
        String authToken;
        if (requestHeader != null && requestHeader.startsWith("Bearer")) {
            authToken = requestHeader.substring(7);
            // 验证token是否过期,包含了验证jwt是否正确
            try {
                Boolean flag = JwtTokenUtil.isTokenExpired(authToken);
                if(flag) {
                    RenderUtil.renderJson(response, new ErrorResponseData(BizExceptionEnum.TOKEN_EXPIRED.getCode(), BizExceptionEnum.TOKEN_ERROR.getMessage()));
                    return false;
                }
            } catch (JwtException e) {
                // 异常表示token解析失败
                RenderUtil.renderJson(response, new ErrorResponseData(BizExceptionEnum.TOKEN_ERROR.getCode(), BizExceptionEnum.TOKEN_ERROR.getMessage()));
                return false;
            }
        } else {
            // header没有带Bearer字段
            RenderUtil.renderJson(response, new ErrorResponseData(BizExceptionEnum.TOKEN_ERROR.getCode(), BizExceptionEnum.TOKEN_ERROR.getMessage()));
            return false;
        }
        return true;
    }
}







































