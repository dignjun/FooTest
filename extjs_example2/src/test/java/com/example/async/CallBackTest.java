package com.example.async;

import org.junit.Test;

import java.util.Scanner;

/**
 * 方法的异步调用，回调测试
 */
public class CallBackTest {
    @Test
    public void test1() throws InterruptedException {
        Server server = new Server();
        Client client = new Client(server);

        client.sendMessage("abcd");

        Thread.sleep(10000);
    }

}
