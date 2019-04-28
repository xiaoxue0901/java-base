package com.autumn.demo.javabase.ybtranstb;

/**
 * @author xql132@zcsmart.com
 * @date 2019/4/18 16:26
 * @description
 */
public class NotifyListenerImpl<T> implements NotifyListener<T> {
    @Override
    public void dispatcherResp(T data, Object obj) {
        // 正常终止任务, 并设置响应对象
        ((MessageReceivedFuture)obj).done(data);
    }
}
