package com.example.async;

/**
 * server
 */
public class Server {

    public void getClientMsg(String msg, CallBack callBack) {
        System.out.println("Server msg:" + msg);
        try {
            Thread.sleep(3000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Server sleep over");
        String status = "200";
        callBack.precess(status);
    }

}
