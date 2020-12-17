package com.autumn.demo.javabase.reflect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;

/**
 * @author xql132@zcsmart.com
 * @date 2019/11/27 17:18
 * @description
 */
@Slf4j
public class AnnonationUse {
    public <T> T getCommonResult(String source, Class<T> clazz) {
        T body = null;
        try {
            // 将一个字符串解析并赋值给A001Body
            // 获取body对象
            body = clazz.newInstance();
            // 获取属性
            Field[] fields = clazz.getDeclaredFields();
            // 获取每个属性对应的注解
            int startIndex = 0;
            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                BodyAnnonation bodyAnnonation = f.getAnnotation(BodyAnnonation.class);
                f.setAccessible(true);
                // 获取注解信息
                int len = bodyAnnonation.len();

//                log.info("起始标识:{}, 长度:{}", startIndex, len);
                String x = source.substring(startIndex, startIndex + len);
//                log.info("临时解析结果:{}", x);
                // 获取编码类型
                String code = bodyAnnonation.code();
                switch (code) {
                    case "STR":
                        f.set(body, x);
                        break;
                    case "HEX":
                        f.set(body, x);
                        break;
                    case "INT":
                        f.set(body, Integer.valueOf(x));
                        break;
                    default:
                        f.set(body, x);
                }
                startIndex = startIndex + len;

            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return body;
    }

    public <T> T getCommonResult2(byte[] source, Class<T> clazz) throws Exception {
        T body = null;
        try {
            // 将一个字符串解析并赋值给A001Body
            // 获取body对象
            body = clazz.newInstance();
            // 获取属性
            Field[] fields = clazz.getDeclaredFields();
            // 获取每个属性对应的注解
            int startIndex = 0;
            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                BodyAnnonation bodyAnnonation = f.getAnnotation(BodyAnnonation.class);
                f.setAccessible(true);
                // 获取注解信息
                int len = bodyAnnonation.len();

                log.info("起始标识:{}, 长度:{}", startIndex, len);
                byte[] res = new byte[len];
                System.arraycopy(source, startIndex, res, 0, len);
                String x = new String(res, "gbk").trim();
                log.info("临时解析结果:{}", x);
                // 获取编码类型
                String code = bodyAnnonation.code();
                switch (code) {
                    case "STR":
                        f.set(body, x);
                        break;
                    case "INT":
                        f.set(body, Integer.valueOf(x));
                        break;
                    default:
                        throw new Exception();
                }
                startIndex = startIndex + len;

            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return body;
    }

    public A001Body getResult() {
        A001Body body = null;
        try {
            // 将一个字符串解析并赋值给A001Body
            String source = "李世民汉32";
            // 获取body对象
            Class clazz = A001Body.class;
            body = (A001Body) clazz.newInstance();
            // 获取属性
            Field[] fields = clazz.getDeclaredFields();
            // 获取每个属性对应的注解
            int startIndex = 0;
            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                BodyAnnonation bodyAnnonation = f.getAnnotation(BodyAnnonation.class);
                f.setAccessible(true);
                // 获取注解信息
                int len = bodyAnnonation.len();

                log.info("起始标识:{}, 长度:{}", startIndex, len);
                String x = source.substring(startIndex, startIndex + len);
                log.info("临时解析结果:{}", x);
                // 获取编码类型
                String code = bodyAnnonation.code();
                switch (code) {
                    case "STR":
                        f.set(body, x);
                        break;
                    case "HEX":
                        f.set(body, x);
                        break;
                    case "INT":
                        f.set(body, Integer.valueOf(x));
                        break;
                    default:
                        f.set(body, x);
                }
                startIndex = startIndex + len;

            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return body;
    }

    public static void main(String[] args) throws Exception {
//        A001Body body = new AnnonationUse().getResult();
//        log.info("结果:{}", JSON.toJSONString(body));

//        A001Body body1 = new AnnonationUse().getCommonResult("周恩来汉54", A001Body.class);
//        log.info("通用结果1:{}", JSON.toJSONString(body1));
//        A001Body body2 = new AnnonationUse().getCommonResult("长白山山99", A001Body.class);
//        log.info("通用结果2:{}", JSON.toJSONString(body2));

        String a = "朱王                明99";
        log.info("gbk编码长度:{}", a.getBytes("gbk").length);
        log.info("utf-8编码长度:{}", a.getBytes().length);
        A001Body body3 = new AnnonationUse().getCommonResult2(a.getBytes("gbk"), A001Body.class);
        log.info("通用结果3:{}", JSON.toJSONString(body3));
    }

}
