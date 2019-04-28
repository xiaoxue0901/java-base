package com.autumn.demo.javabase.ybtranstb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xql132@zcsmart.com
 * @date 2019/4/18 18:54
 * @description
 */
@Component
public class Btest {
    @Autowired
    ServerChannel serverChannel;

    public void getBInfo(){

        String x = serverChannel.getMap().get("a");
        System.out.println("是否能渠道值:"+x);
        System.out.println("Btest注入的值: "+serverChannel.hashCode());
    }
}
