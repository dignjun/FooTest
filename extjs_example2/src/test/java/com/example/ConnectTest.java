package com.example;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectTest {
    @Test
    public void test1() {
        Thread t = new Thread() {
            @Override
            public void run(){
                try {
                    boolean connectable = false;
                    boolean timeout = false;
                    long timeouttime = System.currentTimeMillis() + (5 * 60 * 1000);
                    while (!connectable && !timeout) {
                        System.out.println("-----------------预置本体的安装---------------");
                        Thread.sleep(10000L);
                        connectable = isHostConnectable("127.0.0.1", 9111);
                        if(connectable) {
                            System.out.println("执行预置本体的安装。。。");
                        }
                        if(System.currentTimeMillis() > timeouttime) {
                            timeout = true;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        t.run();
    }

    private boolean isHostConnectable(String host, int port) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(host, port));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
