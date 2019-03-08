package com.autumn.demo.javabase.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xql132@zcsmart.com
 * @date 2019/3/4 15:33
 * @description 阿里巴巴开源框架: FastJson的使用
 * FastJson对于json格式字符串的解析主要用到了下面三个类：
 * 1.JSON：fastJson的解析器，用于JSON格式字符串与JSON对象及javaBean之间的转换
 * 2.JSONObject：fastJson提供的json对象
 * 3.JSONArray：fastJson提供json数组对象
 */
@Slf4j
public class FastJsonApplication {
    /***************定义3个json格式的字符串*************************/
    //json字符串-简单对象型
    private static final String  JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\":12}";

    //json字符串-数组类型
    private static final String  JSON_ARRAY_STR = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";

    //复杂格式json字符串
    private static final String  COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";

    /**
     * json字符串-简单对象型到JSONObject的转换
     */
    public static void testJSONStrToJSONObject() {
        log.info("**************json字符串转为fastJson定义的JSONObject定义的json对象*start******************");
        JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);
        log.info("studentName={}", jsonObject.getString("studentName"));
        log.info("studenAge={}", jsonObject.getString("studentAge"));
        log.info("**************json字符串转为fastJson定义的JSONObject定义的json对象*end******************");
    }

    /**
     * JSONObject到json字符串-简单对象型的转换
     */
    public static void testJSONObjectToJsonStr() {
        log.info("**************fastJson提供的的JSONObject json对象转为json字符串*start******************");
        // 已知JSONObject, 目标要转换为json字符串
        JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);

        // 转为json字符串
        // 方式1
        String jsonStr1 = JSONObject.toJSONString(jsonObject);
        log.info("json字符串: {}", jsonStr1);
        // 方式2
        String jsonStr2 = jsonObject.toJSONString();
        log.info("json字符串: {}", jsonStr1);
        log.info("**************fastJson提供的的JSONObject json对象转为json字符串*end******************");
    }

    /**
     * json字符串-数组类型到JSONArray的转换
     */
    public static void testJSONStrToJSONArray() {
        // JSONArray中存的是JSONObject
        JSONArray jsonArray = JSONArray.parseArray(JSON_ARRAY_STR);
        log.info("使用foreach方式遍历");
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            log.info("studentName={}", jsonObject.getString("studentName"));
            log.info("studentAge={}", jsonObject.getString("studentAge"));
        }
        log.info("使用i++方式遍历");
        for (int i =0; i<jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            log.info("studentName={}", jsonObject.getString("studentName"));
            log.info("studentAge={}", jsonObject.getString("studentAge"));
        }

    }

    /**
     * 复杂json格式字符串到JSONObject的转换
     */
    public static void testComplexJSONStrToJSONObject() {
        JSONObject jsonObject = JSONObject.parseObject(COMPLEX_JSON_STR);
        String teacherName = jsonObject.getString("teacherName");
        Integer age = jsonObject.getInteger("teacherAge");
        log.info("teacherName={}, age={}", teacherName, age);
        // javaBean被解析为JSONObject
        JSONObject jsonCource = jsonObject.getJSONObject("course");
        String cource = jsonCource.getString("courseName");
        log.info("courseName={}", cource);

        JSONArray jsonArray = jsonObject.getJSONArray("students");
        for (Object obj : jsonArray) {
            JSONObject arry = (JSONObject) obj;
            log.info("studentName={}", arry.getString("studentName"));
            log.info("studentAge={}", arry.getString("studentAge"));
        }

    }

    /**
     * 复杂JSONObject到json格式字符串的转换
     */
    public static void testJSONObjectToComplexJSONStr() {

    }


    public static void main(String[] args) {
        testComplexJSONStrToJSONObject();
        testJSONStrToJSONArray();
        testJSONObjectToJsonStr();
        testJSONStrToJSONObject();
    }





}

