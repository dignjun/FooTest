package com.example.model.auth.context;

import com.example.model.auth.AbstractLoginUser;

/**
 * <pre>
 * 当前登录用户的临时保存容器
 *
 *  说明：
 *     当OPEN_UP_FLAG标识在ThreadLocal里为true
 * </pre>
 *
 * @author DINGJUN
 * @date 2019.03.13
 */
public class LoginUserHolder {
    private static final ThreadLocal<Boolean> OPEN_UP_FLAG = new ThreadLocal<>();
    private static final ThreadLocal<AbstractLoginUser> LOGIN_USER_HOLDER = new ThreadLocal<>();

    /**
     * 初始化
     */
    public static void init(){
        OPEN_UP_FLAG.set(true);
    }

    /**
     * 如果OPEN_UP_FLAG没有开启,会设置失败
     * @param abstractLoginUser
     */
    public static void set(AbstractLoginUser abstractLoginUser){
        Boolean boo = OPEN_UP_FLAG.get();
        if(null == boo || boo.equals(false)) {
            return;
        } else {
            LOGIN_USER_HOLDER.set(abstractLoginUser);
        }
    }

    /**
     * 如果标识没有开启,则会获取失败
     * @return
     */
    public static AbstractLoginUser get(){
        Boolean boo = OPEN_UP_FLAG.get();
        if(null == boo || boo.equals(false)){
            return null;
        } else {
            return LOGIN_USER_HOLDER.get();
        }
    }

    /**
     * 删除保存的用户信息
     */
    public static void remove(){
        OPEN_UP_FLAG.remove();
        LOGIN_USER_HOLDER.remove();
    }
}



























