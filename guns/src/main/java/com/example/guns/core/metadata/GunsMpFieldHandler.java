package com.example.guns.core.metadata;

import com.example.core.metadata.CustomMetaObjectHandler;
import com.example.guns.core.shiro.ShiroKit;
import org.springframework.stereotype.Component;

/**
 * 字段填充器
 *
 * @author DINGJUN
 * @date 2019.03.19
 */
@Component
public class GunsMpFieldHandler extends CustomMetaObjectHandler {
    @Override
    protected Object getUserUniqueId() {
        try {
            return ShiroKit.getUser().getId();
        } catch (Exception e) {
            // 如果获取不到当前用户就存空id
            return "";
        }
    }
}















































