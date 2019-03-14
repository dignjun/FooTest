package com.example.guns.core.log.factory;

import com.example.core.util.SpringContextHolder;
import com.example.guns.core.log.LogManager;
import com.example.guns.modular.system.entity.LoginLog;
import com.example.guns.modular.system.mapper.LoginLogMapper;
import com.example.guns.modular.system.mapper.OperationLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * 日志操作任务创建工厂
 *
 * @author DINGJUN
 * @date 2019.03.14
 */
public class LogTaskFactory {
    private static Logger logger = LoggerFactory.getLogger(LogManager.class);
    private static LoginLogMapper loginLogMapper = SpringContextHolder.getBean(LoginLogMapper.class);
    private static OperationLogMapper operationLogMapper = SpringContextHolder.getBean(OperationLogMapper.class);

    public static TimerTask loginLog(final Long userId, final String ip){
        return () -> {
            try {
                logfa
            }
        };
    }
}






























