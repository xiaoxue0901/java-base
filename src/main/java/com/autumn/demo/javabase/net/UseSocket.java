package com.autumn.demo.javabase.net;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Scanner;

/**
 * @author xql132@zcsmart.com
 * @date 2021/1/7
 * @time 14:21
 * @description
 */
@Slf4j
public class UseSocket {

    /**
     * Socket使用
     */
    public static void useSocket() {
        try {
            // 打开一个套接字:启动该程序内部和外部之间的通信. 如果连不到服务, 这个会无期限的阻塞
            Socket socket = new Socket("time-A.timefreq.bldrdoc.gov", 13);
            // 设置超时时间: 单位: 毫秒.
            socket.setSoTimeout(10000);
            // getInputStream():获取从socket中读取数据的流, 返回InputStream对象.
            InputStream inputStream = socket.getInputStream();
            // getOutputStream(): 获取可以向套接字写出数据的流
            OutputStream outputStream = socket.getOutputStream();
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNext()) {
                log.info("scanner:{}", scanner.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Socket使用
     * @param host
     * @param ip
     */
    public static void creatSocket(String host, int ip) {
        // 构建无连接的套接字
        Socket socket = new Socket();
        SocketAddress address = new InetSocketAddress(host, ip);
        try {
            // 设置超时时间, 连接address
            socket.connect(address, 10000);
            // 设置套接字读请求的阻塞时间. 超过指定时间, 抛出InterruptedIOException
            socket.setSoTimeout(60000);
            // 测试是否连接: true:已连接
            boolean connSta = socket.isConnected();
            // 套接字关闭状态: true:已关闭
            boolean closeSta = socket.isClosed();
            log.info("connSta:{}, closeSta:{}", connSta, closeSta);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void useInetAddress() {
        String host = "time-A.timefreq.bldrdoc.gov";
        try {
            // getByName(): 返回代表某个主机的InetAddress对象
            InetAddress address = InetAddress.getByName(host);
            // address封装了4个字节的序列, getAddress()访问字节序列
            byte[] addressBytes = address.getAddress();
            log.info("remote addressByte:{}", JSON.toJSONString(addressBytes));
            // 一个主机名可能会对应多个因特网地址, 用来实现负载均衡. getAllByName(): 获得所有主机
            InetAddress[] addresses = InetAddress.getAllByName("google.com");
            for(InetAddress add: addresses) {
                log.info("地址:hostName:{}, hostAddress:{}", add.getHostName(), add.getHostAddress());
            }
            // 获取本地主机地址
            InetAddress localHost = InetAddress.getLocalHost();
            // 返回一个由十进制数组成的字符串
            String hostAddress = localHost.getHostAddress();
            // 返回主机名
            String hostName = localHost.getHostName();
            log.info("hostAddress:{}, hostName:{}", hostAddress, hostName);


        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

}
