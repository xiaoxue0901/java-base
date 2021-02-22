package com.autumn.demo.netty.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/22
 * @time 8:40 下午
 * @description BIO
 */
@Slf4j
public class Server {
    /** 默认的端口号*/
    public static final int DEFAULT_PORT = 7777;

    /** 单例的ServerSocket*/
    private static ServerSocket serverSocket;


    public static void start() throws IOException {
        start(DEFAULT_PORT);
    }

    public synchronized static void start(int defaultPort) throws IOException {
        if (serverSocket != null) {
            return;
        }
        try {
            serverSocket = new ServerSocket(defaultPort);
            log.info("服务端已启动, 端口号:{}, ip:{}", defaultPort, serverSocket.getInetAddress().getHostAddress());
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ServerHandler(socket)).start();
            }
        }finally {
            if (serverSocket!=null) {
                log.info("服务端已关闭");
                serverSocket.close();
                serverSocket = null;
            }
        }

    }


}
