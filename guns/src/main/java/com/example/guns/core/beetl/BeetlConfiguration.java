package com.example.guns.core.beetl;

import com.example.core.util.ToolUtil;
import com.example.guns.core.util.KaptchaUtil;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;

import java.util.HashMap;
import java.util.Map;

import static com.example.guns.core.common.constant.Const.DEFAULT_SYSTEM_NAME;
import static com.example.guns.core.common.constant.Const.DEFAULT_WELCOME_TIP;

/**
 * beetl拓展配置,绑定一些工具类,方便在模板中直接调用
 *
 * @author DINGJUN
 * @date 2019.03.14
 */
public class BeetlConfiguration extends BeetlGroupUtilConfiguration {
    @Override
    protected void initOther() {
        //全局共享变量
        Map<String, Object> shared = new HashMap<>();
        shared.put("systemName", DEFAULT_SYSTEM_NAME);
        shared.put("welcomeTip", DEFAULT_WELCOME_TIP);
        groupTemplate.setSharedVars(shared);

        //全局共享方法
        groupTemplate.registerFunctionPackage("shiro", new ShiroExt());
        groupTemplate.registerFunctionPackage("tool", new ToolUtil());
        groupTemplate.registerFunctionPackage("kaptcha", new KaptchaUtil());
    }
}



















