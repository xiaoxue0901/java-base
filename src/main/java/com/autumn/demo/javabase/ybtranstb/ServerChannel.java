package com.autumn.demo.javabase.ybtranstb;

import com.autumn.demo.javabase.ybtranstb.entity.HfResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author xql132@zcsmart.com
 * @date 2019/4/18 16:01
 * @description
 */
@Slf4j
@Data
@Component
public class ServerChannel {
    // 成员变量: 存放异步响应结果
    private ConcurrentHashMap<String, MessageReceivedFuture> resultMap = new ConcurrentHashMap<>();

    private HashMap<String, String> map = new HashMap<>();

    /**
     * 主线程获取异步响应结果
     * @param key
     * @throws TimeoutException
     */
    public void getResponse_by(String key, HfResponse response) throws TimeoutException {
        MessageReceivedFuture future = resultMap.get(key);
        log.info("响应信息:{}", future);
        // 调用监听器的回调方法: 设置response, 并终止此线程
//        future.getNotifyListener().dispatcherResp(response, future);
        // 上面代码等价于
        future.done(response);
    }

    public void setHfResponse(String key, HfResponse response) {
        MessageReceivedFuture<HfResponse> messageReceivedFuture = new MessageReceivedFuture<>();
        // 设置响应信息
//        messageReceivedFuture.setMsg(response);
        // 在getResponse_by中会直接调用
//        messageReceivedFuture.setNotifyListener(new NotifyListenerImpl());
        resultMap.put(key, messageReceivedFuture);
    }

    public HfResponse getHfResponse(String key) {
        MessageReceivedFuture<HfResponse> messageReceivedFuture = resultMap.get(key);
        HfResponse response = null;
        try {
             response = messageReceivedFuture.get(10, TimeUnit.SECONDS);
             resultMap.remove(key);
        } catch (TimeoutException e) {
            log.error("等待超时:", e);
        }
         return response;
    }



}
