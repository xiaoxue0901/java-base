package com.autumn.demo.javabase.net;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.*;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
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

    public static void useServerSocket() {
        try {
            // 创建一个监听端口的服务器套接字
            ServerSocket serverSocket = new ServerSocket(8189);
            // 等待连接. accept()阻塞当前线程直到建立连接为止, 返回一个
            Socket socket = serverSocket.accept();
            // 获取可以从套接字中读取数据的流
            InputStream in = socket.getInputStream();
            //获取可以向套接字写出数据的流
            OutputStream out = socket.getOutputStream();

            //控制台输入
            Scanner scanner = new Scanner(in);
            //控制台打印
            PrintWriter writer = new PrintWriter(out, true);
            writer.println("Hello! Enter BYE to exit");
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                writer.println("Echo:"+line);
                if (line.trim().equals("BYE")){
                    break;
                }
            }
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 半关闭: 套接字连接的一端可以终止其输出, 同时仍旧可以接受另一端的数据
     */
    public static void halfColse(String host, int port) {
        // 创建客户端套接字
        try {
            Socket socket = new Socket(host, port);
            Scanner scanner = new Scanner(socket.getInputStream());
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            // send request data
            printWriter.write("eoch");
            printWriter.flush();
            // 关闭输出
            socket.shutdownOutput();
            // 输出关闭, 返回true
            boolean isOutputShutdown = socket.isOutputShutdown();
            // 输入关闭, 返回true
            boolean isInputShutdown = socket.isInputShutdown();
            log.info("socket isOutputShutdown:{}, isInputShutdown:{}", isOutputShutdown, isInputShutdown);
            // 关闭输入流
            socket.shutdownInput();
            // 读服务response
            while (scanner.hasNextLine()) {
                log.info("response:{}", scanner.nextLine());
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 可中断套接字
     */
    public static void useSocketChannel(String host, int port) {
        try {
            // 套接字有连接阻塞, 读写阻塞.
            // 创建一个地址对象.
            InetSocketAddress address = new InetSocketAddress(host, port);
            // 创建的地址是否能被解析: 不能解析返回true
            boolean unreasolved = address.isUnresolved();
            // 中断套接字操作: 打开一个套接字通道, 并将其连接到远程地址
            SocketChannel channel = SocketChannel.open(address);
            // channel中没有流, 通过Buffer对象实现了read和write方法.
            // 使用Scanner从SocketChannel获取信息
            Scanner in = new Scanner(channel);
            // 将通道转为输出流: 创建一个输出力, 用以从指定的通道写入数据
            OutputStream outputStream = Channels.newOutputStream(channel);
            // 创建一个输入流, 用以从指定的通道读取数据
            InputStream inputStream = Channels.newInputStream(channel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
