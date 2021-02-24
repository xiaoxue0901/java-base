package com.autumn.demo.netty.bio;

import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

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

    static ArrayBlockingQueue workQueue = new ArrayBlockingQueue(10);


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
                // 单线程模式
                // new Thread(new ServerHandler(socket)).start();
                // 线程池模式
                creatPool(new ServerHandler(socket));
            }
        }finally {
            if (serverSocket!=null) {
                log.info("服务端已关闭");
                serverSocket.close();
                serverSocket = null;
            }
        }

    }

    /**
     * 自定义线程池创建
     * @param task
     * @return
     */
    public static ThreadPoolExecutor creatPool(Runnable task) {
       ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 30,10, TimeUnit.SECONDS, workQueue, new ThreadPoolExecutor.AbortPolicy());
       executor.execute(task);
        return executor;
    }


}
