package com.example.async;

/**
 * client
 */
public class Client implements CallBack {

    private Server server;

    public Client(Server server) {
        this.server = server;
    }

    public void sendMessage(final String msg) {
        System.out.println("Client msg:" + msg);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                server.getClientMsg(msg, Client.this);
            }
        });
        thread.setDaemon(false);
        thread.start();
        System.out.println("msg over");
    }

    @Override
    public void precess(String status) {
        System.out.println("status: " + status);
    }
}
