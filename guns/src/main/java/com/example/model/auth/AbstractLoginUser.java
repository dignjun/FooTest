package com.example.model.auth;

import java.util.Set;

/**
 * 登录中的用户信息
 * 因为不知道具体的项目类型是什么,所以是泛型
 * @author DINGJUN
 * @date 2019.03.13
 */
public interface AbstractLoginUser {

    /**
     * 获取用户唯一id
     * @param <T>
     * @return
     */
    <T> T getUserUniqueId();

    /**
     * 获取用户唯一id
     * @param <T>
     * @return
     */
    <T> T getAppId();

    /**
     * 获取角色id的集合
     * @param <T>
     * @return
     */
    <T> Set<T> getRoleIds();

    /**
     * 角色编码集合
     * @param <T>
     * @return
     */
    <T> Set<T> getRoleCodes();

    /**
     * 包含资源权限url
     * @param <T>
     * @return
     */
    <T> Set<T> getResourceUrls();

}
