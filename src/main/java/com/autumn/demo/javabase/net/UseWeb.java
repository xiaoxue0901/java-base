package com.autumn.demo.javabase.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author xql132@zcsmart.com
 * @date 2021/1/8
 * @time 11:12
 * @description
 */
public class UseWeb {

    public static void useUrl(String urlStr) {

        try {
            // 创建URL对象:统一资源定位符
            URL url = new URL(urlStr);
            // 获取资源内容
            InputStream inputStream = url.openStream();
            // 使用InputStream
            Scanner scanner = new Scanner(inputStream);


            // URL可解析的模式: http:,https:,ftp:, 本地文件系统(file:), JAR文件(jar:)
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void useUri(String uriStr) {
        // URI语法: [scheme:]schemeSpecificPart[#fragment]
        // 分层的URI的schemeSpecificPart结构: [//authority][path][?query]
        // 基于服务器的URI: authority结构: [user-info@]host[:port]

        // URI: 统一资源标识符, 是纯粹的语法结构.包含用来指定web资源的字符串的各种组成部分.
        // URI作用之一: 解析标识符并将它分解为各种不同的组成部分, 使用下列方法读取.
        try {
            URI uri = new URI(uriStr);
            uri.getScheme();
            uri.getSchemeSpecificPart();
            uri.getAuthority();
            uri.getUserInfo();
            uri.getHost();
            uri.getPort();
            uri.getPath();
            uri.getQuery();
            uri.getFragment();
            // URI作用: 处理绝对标识符和相对标识符

            // 绝对URI
            String absUri = "http://docs.mycompany.com/api/java/net/ServerSocket.html";
            // 相对URI
            String relaUri = "../../java/net/Socket.html#Socket()";
            // 解析相对URL: absUri和relaUri组合出绝对URI: http://docs.mycompany.com/api/java/net/Socket.html#Socket()
            new URI(absUri).resolve(relaUri);
            String baseUri = "http://docs.mycompany.com/api";
            String otherUri = "http://docs.mycompany.com/api/java/lang/String.html";
            // baseUri和otherUri相对化的URI=java/lang/String.html
            new URI(baseUri).relativize(new URI(otherUri));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public static void  useUrlConnection() {
        //URLConnection的使用步骤
        // 1. 调用 URL 类中的 openConnection 方法获得 URLConnection 对象：
        try {
            URL url = new URL("");
            URLConnection connection = url.openConnection();
            // 2. ）使用以下方法来设置任意的请求属性：
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setIfModifiedSince(1L);
            connection.setUseCaches(true);
            connection.setAllowUserInteraction(true);
            connection.setRequestProperty("a", "aaa");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(20000);
            //3）调用 connect 方法连接远程资源：除了与服务器建立套接字连接外，该方法还可用于向服务器查询头信息
            connection.connect();
            //4.与服务器建立连接后，你可以查询头信息。
            connection.getHeaderFieldKey(0);
            Map<String, List<String>> header = connection.getHeaderFields();
            connection.getContentType();
            connection.getContentLength();
            connection.getContentEncoding();
            connection.getDate();
            connection.getExpiration();
            connection.getLastModified();
            //5.访问资源数据
            // 获取一个输入流用以读取信息
            InputStream inputStream = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
