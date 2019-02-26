package org.smart4j.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Transaction;
import org.smart4j.framework.helper.DataBaseHelper;

import java.lang.reflect.Method;

/**
 * 事物代理
 * Created by DINGJUN on 2018/5/14.
 */
public class TransactionProxy implements Proxy{

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionProxy.class);

    private static final ThreadLocal<Boolean> FLAG_HOLDER = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };

    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result;
        boolean flag = FLAG_HOLDER.get();
        Method method = proxyChain.getTargetMethod();
        if (!flag && method.isAnnotationPresent(Transaction.class)) {
            FLAG_HOLDER.set(true);
            try {
                DataBaseHelper.beginTransaction();
                LOGGER.debug("begin transaction");
                result = proxyChain.doProxyChain();
                DataBaseHelper.commitTransaction();
                LOGGER.debug("commit transaction");
            } catch (Throwable throwable) {
                DataBaseHelper.rollbackTransaction();
                LOGGER.error("rollback transaction");
                throw throwable;
            } finally {
                result = proxyChain.doProxyChain();
            }
            return result;
        }
        return null;
    }
}
