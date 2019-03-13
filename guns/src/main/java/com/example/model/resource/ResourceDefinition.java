package com.example.model.resource;

import java.io.Serializable;
import java.util.Date;

/**
 * api资源声明
 *
 * @author DINGJUN
 * @date 2019.03.13
 */
public class ResourceDefinition implements Serializable {

    /**
     * 应用的标识
     */
    private String appCode;

    /**
     * 控制器类名称
     */
    private String className;

    /**
     * 控制器中的方法名称
     */
    private String methodName;

    /**
     * 资源所属模块
     */
    private String modularCode;

    /**
     * 模块中文名称
     */
    private String modularName;

    /**
     * 资源的标识
     */
    private String code;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源的请求路径
     */
    private String url;

    /**
     * http请求方法
     */
    private String httpMethod;

    /**
     * 是否是菜单（true-是，false-否）
     */
    private Boolean menuFlag;

    /**
     * 是否需要登录
     */
    private Boolean requiredLogin;

    /**
     * 是否需要鉴权
     */
    private Boolean requiredPermission;

    /**
     * 资源添加日期
     */
    private Date createTime;

    /**
     * 初始化资源的机器的ip地址
     */
    private String ipAddress;

}
