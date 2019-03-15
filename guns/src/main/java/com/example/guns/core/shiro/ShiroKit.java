package com.example.guns.core.shiro;

import com.example.core.util.ToolUtil;
import com.example.guns.core.common.constant.Const;
import com.example.guns.core.common.constant.factory.ConstantFactory;
import com.example.guns.core.common.exception.BizExceptionEnum;
import com.example.guns.modular.system.entity.User;
import com.example.model.exception.ServiceException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.List;

/**
 * Shiro工具类
 *
 * @author DINGJUN
 * @date 2019.03.15
 */
public class ShiroKit {
    private static final String NAMES_DELIMETER = ",";
    /**
     * 加盐参数
     */
    public final static String hashAlgorithmName = "MD5";

    /**
     * 循环次数
     */
    public final static int hashIterations = 1024;

    public static String md5(String credentials, String saltSource) {
        Md5Hash salt = new Md5Hash(saltSource);
        return new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations).toString();
    }

    public static String getRandomSalt(int length) {
        return ToolUtil.getRandomString(length);
    }

    /**
     * 获取当前主题
     *
     * @return Subject
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static ShiroUser getUser() {
        if (isGuest()) {
            return null;
        } else {
            return (ShiroUser) getSubject().getPrincipals().getPrimaryPrincipal();
        }
    }

    public static ShiroUser getUserNotNull() {
        if (isGuest()) {
            throw new ServiceException(BizExceptionEnum.NOT_LOGIN);
        } else {
            return (ShiroUser) getSubject().getPrincipals().getPrimaryPrincipal();
        }
    }

    public static Session getSession() {
        return getSubject().getSession();
    }

    public static <T> T getSessionAttr(String key) {
        Session session = getSession();
        return session != null ? (T) session.getAttribute(key) : null;
    }

    /**
     * 设置shiro指定的sessionKey
     *
     * @param key
     * @param value
     */
    public static void setSessionAttr(String key, Object value) {
        Session session = getSession();
        session.setAttribute(key, value);
    }

    /**
     * 移除shiro指定的sessionKey
     * @param key
     */
    public static void removeSessionAttr(String key) {
        Session session = getSession();
        if (session != null)
            session.removeAttribute(key);
    }

    /**
     * 验证当前用户是否属于该角色,使用时与lacksRole搭配使用
     *
     * @param roleName 角色名
     * @return true:属于,false:不属于
     */
    public static boolean hasRole(String roleName) {
        return getSubject() != null && roleName != null
                && roleName.length() > 0 && getSubject().hasRole(roleName);
    }

    /**
     * 当用户不属于该角色时验证通过
     *
     * @param roleName
     * @return
     */
    public static boolean lacksRole(String roleName) {
        return !hasRole(roleName);
    }

    /**
     * 验证当前用户是否属于以下任意一个角色。
     * @param roleNames
     * @return
     */
    public static boolean hasAnyRoles(String roleNames) {
        boolean hasAnyRole = false;
        Subject subject = getSubject();
        if (subject != null && roleNames != null && roleNames.length() > 0) {
            for (String role :
                    roleNames.split(NAMES_DELIMETER)) {
                if (subject.hasRole(role.trim())) {
                    hasAnyRole = true;
                    break;
                }
            }
        }
        return hasAnyRole;
    }

    /**
     * 验证当前用户是否属于以下所有角色。
     *
     * @param roleNames
     * @return
     */
    public static boolean hasAllRoles(String roleNames) {
        boolean hasAllRole = true;
        Subject subject = getSubject();
        if (subject != null && roleNames != null && roleNames.length() > 0) {
            for (String role :
                    roleNames.split(NAMES_DELIMETER)) {
                if (!subject.hasRole(role.trim())) {
                    hasAllRole = false;
                    break;
                }
            }
        }
        return hasAllRole;
    }

    /**
     * 验证当前用户是否拥有制定权限,使用时与lacksPermission搭配使用
     * @param permission
     * @return
     */
    public static boolean hasPermission(String permission) {
        return getSubject() != null && permission != null && permission.length() > 0 && getSubject().isPermitted(permission);
    }

    /**
     * 与hasPermission标签逻辑相反，当前用户没有制定权限时，验证通过。
     * @param permission 权限名
     * @return 拥有权限：true，否则false
     */
    public static boolean lacksPermission(String permission) {
        return !hasPermission(permission);
    }

    /**
     * 已认证通过的用户。不包含已记住的用户，这是与user标签的区别所在。与notAuthenticated搭配使用
     * @return 通过身份验证：true，否则false
     */
    public static boolean isAuthenticated(){
        return getSubject() != null && getSubject().isAuthenticated();
    }

    /**
     * 未认证通过用户.与guest标签的区别是,包含已记住用户
     * @return
     */
    public static boolean notAuthenticated(){return !isAuthenticated();}

    /**
     * 认证通过或已记住的用户.
     * @return
     */
    public static boolean isUser(){return getSubject() != null && getSubject().getPrincipal() != null;}

    /**
     * 验证当前用户是否为访客,即未认证和未记住的用户
     * @return
     */
    public static boolean isGuest(){return !isUser();}

    /**
     * 输出当前用户信息,通常为登录帐号信息
     * @return
     */
    public static String principal(){
        if(getSubject() != null) {
            Object principal = getSubject().getPrincipal();
            return principal.toString();
        }
        return "";
    }

    /**
     * 判断当前用户部门的数据范围集合
     * @return
     */
    public static List<Long> getDeptDataScope(){
        Long deptId = getUser().getDeptId();
        List<Long> subDeptIds = ConstantFactory.me().getSubDeptId(deptId);
        subDeptIds.add(deptId);
        return subDeptIds;
    }

    /**
     * 判断当前用户是否是管理员
     * @return
     */
    public static boolean isAdmin(){
        List<Long> roleList = ShiroKit.getUser().getRoleList();
        for (Long roleId :
                roleList) {
            String singleRoleTip = ConstantFactory.me().getSingleRoleTip(roleId);
            if(singleRoleTip.equals(Const.ADMIN_NAME)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据用户表创建一个shiroUser对象
     * @param user
     * @return
     */
    public static ShiroUser createShiroUser(User user) {
        ShiroUser shiroUser = new ShiroUser();
        if(null == user) {
            return shiroUser;
        }

        shiroUser.setId(user.getUserId());
        shiroUser.setName(user.getName());
        shiroUser.setDeptId(user.getDeptId());
        shiroUser.setAvatar(user.getAvatar());
        shiroUser.setEmail(user.getEmail());
        shiroUser.setDeptName(ConstantFactory.me().getDeptName(user.getDeptId()));
        shiroUser.setAccount(user.getAccount());

        return shiroUser;
    }

}




































