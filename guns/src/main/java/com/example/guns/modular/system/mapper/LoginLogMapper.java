package com.example.guns.modular.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.guns.modular.system.entity.LoginLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author DINGJUN
 * @date 2019.03.14
 */
public interface LoginLogMapper extends BaseMapper<LoginLog> {
    /**
     * 获取登录日志
     */
    List<Map<String, Object>> getLoginLogs(@Param("page") Page page, @Param("beginTime") String beginTime,
                                           @Param("endTime") String endTime, @Param("logName") String logName);
}
