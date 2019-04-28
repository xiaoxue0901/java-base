package com.autumn.demo.javabase.ybtranstb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xql132@zcsmart.com
 * @date 2019/4/18 18:54
 * @description
 */
@Component
public class Atest {
    @Autowired
    ServerChannel serverChannel;

    public void getAInfo(){
        serverChannel.getMap().put("a", "1256328");
        System.out.println("Atest注入的值: "+serverChannel.hashCode());
    }
}
