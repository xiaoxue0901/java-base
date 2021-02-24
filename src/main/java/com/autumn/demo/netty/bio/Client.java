package com.autumn.demo.netty.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/22
 * @time 9:36 下午
 * @description
 */
@Slf4j
public class Client {
    /** 默认的端口号*/
    public static final int DEFAULT_SERVER_PORT = 7777;
    /**服务端IP地址*/
    public static final String DEFAULT_SERVER_IP = "172.80.80.6";

    public static void send(String expression) {
        send(DEFAULT_SERVER_PORT, expression);
    }

    public Client() {
    }

    public static void send(int defaultServerPort, String expression) {
        log.info("算数表达式为:{}", expression);
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket(DEFAULT_SERVER_IP, defaultServerPort);
            log.info("启动客户端:{}", socket.getInetAddress().getHostAddress());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("client:"+expression);
            log.info("send to server succeed");
            log.info("---结果为:{}", in.readLine());
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 方便GC回收
            in = null;
            if (out != null) {
                out.close();
            }
            out = null;
           if (socket !=null) {
               try {
                   socket.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           socket = null;
        }
    }

    public static void main(String[] args) {

    }

}
