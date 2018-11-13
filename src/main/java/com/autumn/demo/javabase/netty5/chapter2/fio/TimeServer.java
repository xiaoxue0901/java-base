package com.autumn.demo.javabase.netty5.chapter2.fio;


import com.autumn.demo.javabase.netty5.chapter2.bio.TimeServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xql132@zcsmart.com
 * @date 2018/3/30
 * @time 14:11
 * @description 伪异步I/O
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
            System.out.println("The time server is start in port : " + port);
            Socket socket = null;
            // 创建I/O任务池
            TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50, 10000);
            while (true) {
                socket = server.accept();
                singleExecutor.execute(new TimeServerHandler(socket));
            }
        } finally {
            if (server != null) {
                System.out.println("The time server close");
                server.close();
                server = null;
            }
        }
    }
}
