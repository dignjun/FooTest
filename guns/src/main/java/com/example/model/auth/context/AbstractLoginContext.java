package com.example.model.auth.context;

import com.example.model.auth.AbstractLoginUser;

/**
 * 快速获取登录信息上下文
 *
 * @author DINGJUN
 * @date 2019.03.13
 */
public interface AbstractLoginContext {
    /**
     * 获取当前用户的token
     */
    String getCurrentUserToken();

    /**
     * 获取当前用户
     */
    <T extends AbstractLoginUser> T getLoginUser();
}
