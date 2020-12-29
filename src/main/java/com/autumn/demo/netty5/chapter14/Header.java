package com.autumn.demo.netty5.chapter14;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xql132@zcsmart.com
 * @date 2019/7/17 9:40
 * @description 1. 消息头Header定义
 */
@Data
public class Header {
    private int crcCode = 0xabef0101;
    private int length; // 消息长度
    private long sessionId; // 会话ID
    private byte type; //消息类型
    private byte priority; // 消息优先级
    private Map<String, Object> attachment = new HashMap<>(); // 附件

}
