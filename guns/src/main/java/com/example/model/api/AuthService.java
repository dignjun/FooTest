package com.example.model.api;

import com.example.model.auth.AbstractLoginUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 鉴权服务,提供颁发,校验,注销等方法
 *
 * @author DINGJUN
 * @date 2019.03.13
 */
@RequestMapping("/api/authService")
public interface AuthService {
    /**
     * 登录(验证账号密码)
     * 若登录成功则返回token,若登录不成功则返回null
     *
     * @param account
     * @param password
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    String login(@RequestParam("account") String account, @RequestParam("password") String password);

    /**
     * 校验token(true-成功,false-失败)
     * @param token
     * @return
     */
    @RequestMapping(value = "/checkToken", method = RequestMethod.POST)
    boolean checkToken(@RequestParam("token") String token);

    /**
     * 注销token
     *
     * @param token
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    void logout(@RequestParam("token") String token);

    /**
     * 通过token获取用户信息
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "getLoginUserByToken", method = RequestMethod.POST)
    AbstractLoginUser getLoginUserByToken(@RequestParam("token") String token);

}
