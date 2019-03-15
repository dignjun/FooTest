package com.example.guns.core.shiro.service;

import com.example.guns.core.shiro.ShiroUser;
import com.example.guns.modular.system.entity.User;
import org.apache.shiro.authc.SimpleAuthenticationInfo;

import java.util.List;

/**
 * 定义Shiro Realm所需数据接口
 * @author DINGJUN
 * @date 2019.03.15
 */
public interface UserAuthService {

    /**
     * 根据帐号获取登录用户
     * @param account 帐号
     * @return
     */
    User user(String account);


    /**
     * 通过系统用户获取shiro用户
     * @param user
     * @return
     */
    ShiroUser shiroUser(User user);

    /**
     * 通过角色id获取权限列表
     * @param roleId
     * @return
     */
    List<String> findPermissionByRoleId(Long roleId);

    /**
     * 通过角色id获取角色名称
     * @param roleId
     * @return
     */
    String findRoleNameByRoleId(Long roleId);

    /**
     * 获取shiro认证信息
     * @param shiroUser
     * @param user
     * @param realmName
     * @return
     */
    SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realmName);
}


































