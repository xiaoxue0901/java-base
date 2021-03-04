package com.autumn.demo.javabase.set.map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.autumn.demo.javabase.bean.Employee;
import com.autumn.demo.javabase.enumerate.base.RainBowEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.WeekdayFunc;

import java.util.*;

/**
 * @author xql132@zcsmart.com
 * @date 2021/1/5
 * @time 18:24
 * @description
 */
@Slf4j
public class UseMap {

    public static void useMap() {
        Map<String, Employee> staff = new HashMap<>();
        Employee harray = new Employee("Harray Hacker");
        Employee lily = new Employee("Lily");
        Employee nana = new Employee("Nana");
        // 映射表添加对象, 返回key存储的上一个值
        Employee previous = staff.put("9996", harray);

        staff.put("9998", lily);
        staff.put("9999", nana);
        String key = "9996";
        log.info("previous:{}", previous);
        // 映射表取对象
        Employee getHar = staff.get("9996");
        // 删除
        Employee removeHar = staff.remove(key);
        // size: 映射表中的元素数量
        int size = staff.size();
        log.info("getHar:{}, removeHar:{}, size:{}", getHar, removeHar, size);


        for (Map.Entry<String, Employee> e : staff.entrySet()) {
            String k = e.getKey();
            Employee v = e.getValue();
            log.info("key:{}, value:{}", k, v);
        }

        Map<String, Employee> map = new HashMap<>();
        map.put(key, harray);
        // 将map的所有条目添加到map中.
        map.putAll(staff);

        // 是否包含key
        boolean conKey = map.containsKey(key);

        // 是否包含key值
        boolean conVal = map.containsValue(harray);
        log.info("conKey:{}, conVal:{}", conKey, conVal);
        // 键/值对集: 键与键/值对形成了一个集, 返回Map.Entry<K,V>对象的集视图
        Set<Map.Entry<String, Employee>> entrySet = map.entrySet();
        // 可以删除entrySet中的元素
        entrySet.remove("9998");
        // 键集:
        Set<String> keys = map.keySet();
        // 可以从keys中删除元素, 同时也从staff中删除.
        keys.remove("9999");
        // 值集合(不是集)
        Collection<Employee> values = map.values();
        // 可以从values中删除元素
        values.remove("9996");
        for (Map.Entry<String, Employee> e : entrySet) {
            if (e.getKey().equals(key)) {
                e.setValue(new Employee("Harray Hacker", 67567D, new Date()));
            }
            log.info("key:{}, value:{}", e.getKey(), e.getValue());
        }
    }

    public static void constructMap() {
        // 构造HashMap
        HashMap hashMap = new HashMap();
        HashMap initHashMap = new HashMap(16);
        HashMap initLoadMap = new HashMap(16, 0.75f);

        // 构造TreeMap
        TreeMap treeMap = new TreeMap((o1, o2) -> {
            return 0;
        });

        TreeMap entryMap = new TreeMap(treeMap);

        SortedMap<String, Integer> sortedMap = new TreeMap<>();
        Comparator comparator = sortedMap.comparator();
        // 返回最小元素
        sortedMap.firstKey();
        // 返回最大元素
        sortedMap.lastKey();
        TreeMap<String, Integer> sortMap = new TreeMap<>(sortedMap);
    }

    /**
     * 弱散列映射表
     */
    public static void useWeakHashMap() {
        WeakHashMap<String, Integer> weakHashMap = new WeakHashMap<>();
    }

    /**
     * 链接散列集和链接映射表: 记住插入元素项的顺序
     */
    public static void useLinkedHash() {
        //
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
    }

    public static void useEnumSet() {
        // 枚举集
        // always
        EnumSet<RainBowEnum> rainBowEnums = EnumSet.allOf(RainBowEnum.class);
        // never
        EnumSet<RainBowEnum> never = EnumSet.noneOf(RainBowEnum.class);
        // yellow
        EnumSet<RainBowEnum> yellow = EnumSet.range(RainBowEnum.ORANGE, RainBowEnum.GREEN);
        // 列举枚举集
        EnumSet<RainBowEnum> set = EnumSet.of(RainBowEnum.ORANGE, RainBowEnum.YELLEOW, RainBowEnum.RED);

        // 枚举映射表
        EnumMap<RainBowEnum, Employee> employeeEnumMap = new EnumMap<RainBowEnum, Employee>(RainBowEnum.class);

    }

    public static void useIdentityHashMap() {
        IdentityHashMap<String, String> identityHashMap = new IdentityHashMap<>();
        String key = "Bob";
        // 键的散列值
        int keyHashCode = System.identityHashCode(key);
        identityHashMap.put(key, "鲍勃");
    }

    public static void map() {
        HashMap<String, List<String>> result = new HashMap<>(2);
        List<String> addUrl = new LinkedList<>();
        addUrl.add("/url1");
        addUrl.add("/url2");
        // result.put("add", JSON.toJSONString(addUrl));
        result.put("add", addUrl);
        List<String> addUrl2 = new LinkedList<>();
        addUrl2.add("/url1");
        addUrl2.add("/url2");
        result.put("update", addUrl2);

        log.info("map:{}", JSON.toJSONString(result));
    }

    public static void main(String[] args) {
        map();

        String result = "{\"add\":[\"/url1\",\"/url2\"],\"update\":[\"/url1\",\"/url2\"]}";
        Map<String,List<String>> map = JSON.parseObject(result,new TypeReference<Map<String,List<String>>>(){});
        map.forEach((k,v) -> {
            log.info("k: :{}, v: :{}", k, v);

        });

    }


}
