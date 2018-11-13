package com.autumn.demo.javabase.netty5.chapter2.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xql132@zcsmart.com
 * @date 2018/3/29
 * @time 11:55
 * @description 同步阻塞式I/O的TimeServer
 */
public class TimeServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                System.out.println("数组值:"+args[0]);
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port:" + port);
            Socket socket = null;
            while (true) {
                socket = server.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                System.out.println("The time server close");
                server.close();
                server = null;
            }
        }

    }
}
