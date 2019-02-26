package org.smart4j.framework.proxy;

/**
 * 代理接口
 * Created by DINGJUN on 2018/5/6.
 */
public interface Proxy {

    /**
     * 执行链式代理
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
