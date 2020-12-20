package com.autumn.springdemo.restTemplate.core;

import com.alibaba.fastjson.JSON;
import com.autumn.springdemo.restTemplate.wx.AccessToken;
import com.autumn.springdemo.restTemplate.wx.WxResp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author xql132@zcsmart.com
 * @date 2018/4/20
 * @time 9:36
 * @description
 */
@Component
@Slf4j
public class HttpUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    @Autowired
    RestTemplate restTemplate;



    /**
     * 获取accessToken
     *
     * @param appId
     * @param appSecret
     * @return
     */
    public String getAccessToken(String appId, String appSecret) {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type={grantType}&appid={appId}&secret={appSecret}";
        Map<String, String> request = new HashMap<>(3);
        request.put("grantType", "client_credential");
        request.put("appId", appId);
        request.put("appSecret", appSecret);
        AccessToken resp = doGet(url, AccessToken.class, request);
        return resp.getAccessToken();
    }


    /**
     * 获取微信ticket
     * @param accessToken
     * @return
     */
    public String getWxTicket(String accessToken) {
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={accessToken}&type=jsapi";
        Map<String, String> request = new HashMap<>(1);
        request.put("accessToken", accessToken);
        WxResp resp = doGet(url, WxResp.class, request);
        return resp.getTicket();
    }


    public Map getWxSignature(String url, String appId, String appSecret) {
        String accessToken =  getAccessToken(appId, appSecret);
        String ticket = getWxTicket(accessToken);
        return getJsApiConfig(url, appId, ticket);
    }
    public <T> T doGet(String url, Class<T> responseClazz, Map<String, String> map) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, map);
        log.info("原始响应报文:{}, header:{}, code:{}", responseEntity, responseEntity.getHeaders(), responseEntity.getStatusCode());
        log.info("原始响应报文:{}", responseEntity.getBody());
        T resp = JSON.parseObject(responseEntity.getBody(), responseClazz);
        logger.info("请求url:{}, 响应报文:{}", url, JSON.toJSONString(resp));
        return resp;
    }

    /**
     *获取JSSDK签名
     *url:当前页面的完整URL，包括参数
     **/
    public Map getJsApiConfig(String url, String appid, String jsapi_ticket){
        String noncestr = UUID.randomUUID().toString().replace("-", "");
        String timestamp = "" + System.currentTimeMillis() / 1000;
        Map<String, String> params = new HashMap<String, String>();
        params.put("jsapi_ticket", jsapi_ticket);
        params.put("noncestr", noncestr);
        params.put("timestamp", timestamp);
        params.put("url", url);
        //1.1 对所有待签名参数按照字段名的ASCII 码从小到大排序（字典序）
        Map<String, String> sortParams = sortAsc(params);
        //1.2 使用URL键值对的格式拼接成字符串
        String str = mapJoin(sortParams, false);
        logger.info("str:{}", str);
        //使用这个方法
        String signature = DigestUtils.sha1Hex(str.getBytes(StandardCharsets.UTF_8));

        Map<String, String> result = new HashMap<String,String>();
        result.put("debug", "false");
        result.put("appId", appid);
        result.put("nonceStr", noncestr);
        result.put("timestamp", timestamp);
        result.put("signature", signature);
        log.info("签名信息:{}", JSON.toJSONString(result));
        return result;
    }


    private HashMap<String, String> sortAsc(Map<String, String> map) {
        HashMap<String, String> tempMap = new LinkedHashMap<String, String>();
        List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(map.entrySet());
        //排序
        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        for (int i = 0; i < infoIds.size(); i++) {
            Map.Entry<String, String> item = infoIds.get(i);
            tempMap.put(item.getKey(), item.getValue());
        }
        return tempMap;
    }


    public static String mapJoin(Map<String, String> map, boolean valueUrlEncode) {
        StringBuilder sb = new StringBuilder();
        for (String key : map.keySet()) {
            if (map.get(key) != null && !"".equals(map.get(key))) {
                try {
                    String temp = (key.endsWith("_") && key.length() > 1) ? key.substring(0, key.length() - 1) : key;
                    sb.append(temp);
                    sb.append("=");
                    //获取到map的值
                    String value = map.get(key);
                    //判断是否需要url编码
                    if (valueUrlEncode) {
                        value = URLEncoder.encode(map.get(key), "utf-8").replace("+", "%20");
                    }
                    sb.append(value);
                    sb.append("&");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public void doGet2() {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx7086d3fb459dbb09&secret=fe14a5b4857e583b443917eba3fc8d4e";
        ResponseEntity<String> resp = restTemplate.getForEntity(url, String.class);
        log.info("响应:{}", JSON.toJSONString(resp));
        logger.info("请求url:{}, 响应报文:{}", url, resp.getBody());
    }


}
