package com.example.guns.modular.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.guns.core.common.page.LayuiPageFactory;
import com.example.guns.modular.system.entity.Notice;
import com.example.guns.modular.system.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 通知表 服务实现类
 * </p>
 *
 */
@Service
public class NoticeService extends ServiceImpl<NoticeMapper, Notice> {

    /**
     * 获取通知列表
     *
     */
    public Page<Map<String, Object>> list(String condition) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.list(page, condition);
    }
}
