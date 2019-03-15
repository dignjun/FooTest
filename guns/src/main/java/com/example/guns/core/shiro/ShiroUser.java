package com.example.guns.core.shiro;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 自定义Authentication对象,使得Subject除了携带用户的登录名还可以携带更多的信息
 *
 * @author DINGJUN
 * @date 2019.03.15
 */
@Data
public class ShiroUser implements Serializable {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 帐号
     */
    private String account;
    /**
     * 姓名
     */
    private String name;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 部门id
     */
    private Long deptId;
    /**
     * 角色集
     */
    private List<Long> roleList;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 角色名称集
     */
    private List<String> roleNames;

}
































