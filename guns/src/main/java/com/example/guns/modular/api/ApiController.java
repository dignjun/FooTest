package com.example.guns.modular.api;

import com.example.core.base.controller.BaseController;
import com.example.core.reqres.response.ErrorResponseData;
import com.example.guns.core.shiro.ShiroKit;
import com.example.guns.core.shiro.ShiroUser;
import com.example.guns.core.util.JwtTokenUtil;
import com.example.guns.modular.system.entity.User;
import com.example.guns.modular.system.mapper.UserMapper;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 接口控制器
 *
 * @author DINGJUN
 * @date 2019.03.19
 */
@RestController
@RequestMapping("/gunsApi")
public class ApiController extends BaseController {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserMapper userMapper;

    /**
     * api登录接口,通过帐号密码获取token
     */
    @RequestMapping("/auth")
    public Object auth(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 封装请求帐号密码为shiro可验证的token
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password.toCharArray());
        // 获取数据库中的帐号密码,准备比对
        User user = userMapper.getByAccount(username);
        String credentials = user.getPassword();
        String salt = user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(salt);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(new ShiroUser(), credentials, credentialsSalt, "");
        // 校验用户帐号密码
        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
        md5CredentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);
        md5CredentialsMatcher.setHashIterations(ShiroKit.hashIterations);
        boolean passwordTrueFlag = md5CredentialsMatcher.doCredentialsMatch(usernamePasswordToken, simpleAuthenticationInfo);
        if (passwordTrueFlag) {
            HashMap<Object, Object> result = new HashMap<>();
            result.put("token", JwtTokenUtil.generateToken(String.valueOf(user.getUserId())));
            return result;
        } else {
            return new ErrorResponseData(500, "帐号密码错误!");
        }
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public Object test(){
        return SUCCESS_TIP;
    }
}










































