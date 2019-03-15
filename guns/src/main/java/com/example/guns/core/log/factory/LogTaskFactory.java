package com.example.guns.core.log.factory;

import com.example.core.util.SpringContextHolder;
import com.example.core.util.ToolUtil;
import com.example.guns.core.common.constant.state.LogSucceed;
import com.example.guns.core.common.constant.state.LogType;
import com.example.guns.core.log.LogManager;
import com.example.guns.modular.system.entity.LoginLog;
import com.example.guns.modular.system.entity.OperationLog;
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
        return new TimerTask() {
            @Override
            public void run() {
                LoginLog loginLog = LogFactory.createLoginLog(LogType.LOGIN, userId, null, ip);
                try {
                    loginLogMapper.insert(loginLog);
                } catch (Exception e) {
                    logger.error("创建登录日志异常!", e);
                }
            }
        };
    }

    public static TimerTask loginLog(final String userName, final String msg, final String ip){
        return new TimerTask() {
            @Override
            public void run() {
                LoginLog loginLog = LogFactory.createLoginLog(LogType.LOGIN, null, "帐号" + userName + "," +msg, ip);
                try {
                    loginLogMapper.insert(loginLog);
                } catch (Exception e) {
                    logger.error("创建登录失败异常!", e);
                }
            }
        };
    }

    public static TimerTask exitLog(final Long userId, final String ip) {
        return new TimerTask() {
            @Override
            public void run() {
                LoginLog loginLog = LogFactory.createLoginLog(LogType.EXIT, userId, null, ip);
                try {
                    loginLogMapper.insert(loginLog);
                } catch (Exception e) {
                    logger.error("创建退出日志异常" ,e);
                }
            }
        };
    }

    public static TimerTask businessLog(final Long userId, final String bussinessName, final String clazzName, final String methodName, final String msg) {
        return new TimerTask() {
            @Override
            public void run() {
                OperationLog operationLog = LogFactory.createOperationLog(LogType.BUSSINESS, userId, bussinessName, clazzName, methodName, msg, LogSucceed.SUCCESS);
                try {
                    operationLogMapper.insert(operationLog);
                } catch (Exception e) {
                    logger.error("创建业务日志异常", e);
                }
            }
        };
    }

    public static TimerTask exceptionLog(final Long userId, final Exception exception) {
        return new TimerTask() {
            @Override
            public void run() {
                String exceptionMsg = ToolUtil.getExceptionMsg(exception);
                OperationLog operationLog = LogFactory.createOperationLog(LogType.EXCEPTION, userId, "", null, null, exceptionMsg, LogSucceed.FAIL);
                try {
                    operationLogMapper.insert(operationLog);
                } catch (Exception e) {
                    logger.error("创建异常日志异常", e);
                }
            }
        };
    }
}






























