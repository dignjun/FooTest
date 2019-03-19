package com.example.guns.core.interceptor;

import com.example.guns.core.shiro.ShiroKit;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filter that allows access to resources if the accessor is a known user, which is defined as
 * having a known principal.  This means that any user who is authenticated or remembered via a
 * 'remember me' feature will be allowed access from this filter.
 * <p/>
 * If the accessor is not a known user, then they will be redirected to the {@link #setLoginUrl(String) loginUrl}</p>
 *
 * @author DINGJUN
 * @date 2019.03.19
 */
public class GunsUserFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object object) throws Exception {
        if (isLoginRequest(servletRequest, servletResponse)) {
            return true;
        } else {
            Subject subject = getSubject(servletRequest, servletResponse);
            // if principal is not null, then the user is know and should be allowed access.
            return subject.getPrincipal() != null;
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(servletRequest);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(servletResponse);
        /**
         * 如果是ajax请求则不进行跳转
         */
        if (httpServletRequest.getHeader("x-requested-with") != null && httpServletRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            httpServletResponse.setHeader("sessionstatus", "timeout");
            return false;
        } else {
            /**
             * 第一次点击
             */
            String referer = httpServletRequest.getHeader("Referer");
            if (referer == null) {
                saveRequestAndRedirectToLogin(servletRequest, servletResponse);
                return false;
            } else {
                /**
                 * 从别的页面跳转过来
                 */
                if (ShiroKit.getSession().getAttribute("sessionFlag") == null) {
                    httpServletRequest.setAttribute("tips", "session超时");
                    httpServletRequest.getRequestDispatcher("/login").forward(servletRequest, servletResponse);
                    return false;
                } else {
                    saveRequestAndRedirectToLogin(servletRequest, servletResponse);
                    return false;
                }
            }
        }
    }
}


