package com.autumn.demo.javabase.ybtranstb;

import java.util.HashMap;

/**
 * @author xql132@zcsmart.com
 * @date 2019/4/18 15:02
 * @description 通知监听器
 */
public interface NotifyListener<T> {

    void dispatcherResp(T data, Object obj);

}
