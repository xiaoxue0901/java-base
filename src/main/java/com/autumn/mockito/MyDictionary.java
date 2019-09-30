package com.autumn.mockito;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xql132@zcsmart.com
 * @date 2019/9/27 14:22
 * @description 测试@InjectMock注解使用类
 */
public class MyDictionary {
    Map<String, String> wordMap;

    /**
     * use a mock with spy, 通过构造函数手动将mock注入
     * @param wordMap
     */
    public MyDictionary(Map<String, String> wordMap) {
        this.wordMap = wordMap;
    }

    public MyDictionary() {
        wordMap = new HashMap<String, String>();
    }

    public void add(final String word, final String meaning) {
        wordMap.put(word, meaning);

    }

    public String getMeaning(final String word) {
        return wordMap.get(word);
    }
}
