package com.example.tool.io;

/**
 * 行处理器
 * Created by DINGJUN on 2019.03.11.
 */
public interface LineHandler {
    /**
     * 处理一行数据，可以编辑后存入指定地方
     * @param line 行
     */
    void handle(String line);
}
