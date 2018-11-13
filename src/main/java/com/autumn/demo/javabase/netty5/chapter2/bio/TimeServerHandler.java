package com.autumn.demo.javabase.netty5.chapter2.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * @author xql132@zcsmart.com
 * @date 2018/3/29
 * @time 13:20
 * @description Runnable: 处理Socket链路
 */
public class TimeServerHandler implements Runnable {
    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null; //输入流
        PrintWriter out = null;     //输出流
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);
            String currentTime = null;
            String body = null;
            while (true) {
                body = in.readLine();
                if (body == null) {
                    break;
                }
                System.out.println("The time server receive order : " + body);
                currentTime = "TimeOrder1".equals(body) ? "current time" : "BAD ORDER";
                out.println(currentTime);
            }
        } catch (IOException e) {

            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                    out = null;
                }
                if (this.socket != null) {
                    this.socket.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

}
