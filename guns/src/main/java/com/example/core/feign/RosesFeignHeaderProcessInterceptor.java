package com.example.core.feign;

import com.example.core.feign.x.RequestInterceptor;
import com.example.core.feign.x.RequestTemplate;
import com.example.core.util.HttpContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author DINGJUN
 * @date 2019.03.13
 */
@Slf4j
public class RosesFeignHeaderProcessInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest request = HttpContext.getRequest();

        if (request == null) {
            if (log.isDebugEnabled()) {
                log.debug("被调环境中不存在request对象，则不往header里添加当前请求环境的header!");
            }
            return;
        } else {
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    String values = request.getHeader(name);
                    requestTemplate.header(name, values);
                }
            }
        }
        this.addOtherHeaders(requestTemplate);
    }

    public void addOtherHeaders(RequestTemplate requestTemplate) {

    }

}
