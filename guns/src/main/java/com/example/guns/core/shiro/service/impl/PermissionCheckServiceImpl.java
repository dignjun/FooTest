package com.example.guns.core.shiro.service.impl;

import com.example.core.util.SpringContextHolder;
import com.example.guns.core.common.constant.state.ManagerStatus;
import com.example.guns.core.shiro.ShiroUser;
import com.example.guns.core.shiro.service.PermissionCheckService;
import com.example.guns.core.shiro.service.UserAuthService;
import com.example.guns.modular.system.entity.User;
import com.example.guns.modular.system.mapper.MenuMapper;
import com.example.guns.modular.system.mapper.UserMapper;
import com.example.guns.modular.system.service.UserService;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author DINGJUN
 * @date 2019.03.15
 */
@Service
@DependsOn("springContextHolder")
@Transactional(readOnly = true)
public class PermissionCheckServiceImpl implements UserAuthService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private UserService userService;

    public static UserAuthService me(){
        return SpringContextHolder.getBean(UserAuthService.class);
    }

    @Override
    public User user(String account) {
        User user = userMapper.getByAccount(account);
        if(null == user) {
            throw new CredentialsException();
        }
        if(!user.getStatus().equals(ManagerStatus.OK)){
            throw new LockedAccountException();
        }
        return user;
    }

    @Override
    public ShiroUser shiroUser(User user) {
        return null;
    }

    @Override
    public List<String> findPermissionByRoleId(Long roleId) {
        return null;
    }

    @Override
    public String findRoleNameByRoleId(Long roleId) {
        return null;
    }

    @Override
    public SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realmName) {
        return null;
    }
}
