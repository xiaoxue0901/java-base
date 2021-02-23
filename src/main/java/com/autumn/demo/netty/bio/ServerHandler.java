package com.autumn.demo.netty.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author xql132@zcsmart.com
 * @date 2021/2/22
 * @time 8:49 下午
 * @description
 */
@Slf4j
public class ServerHandler implements Runnable {
    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String expression;
            String result;
            while (true) {
                if ((expression = in.readLine()) == null) {
                    break;
                }
                log.info("服务端收到信息:{}", expression);
                result = Caculator.cal(expression);
                out.println("server:"+result);

            }

        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        } finally {
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
}
