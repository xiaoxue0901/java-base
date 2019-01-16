package com.autumn.demo.javabase.base.demoenum;

import com.autumn.demo.javabase.designpattern.demo07_builder.Client;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

/**
 * @author xql132@zcsmart.com
 * @date 2019/1/14 17:40
 * @description 用接口管理枚举
 * 枚举的值values()是存在数组中的.
 */
public interface OrgnizeEnums {
    // 自定义水果枚举
    enum FruitEnum implements OrgnizeEnums {
        PEACH, GRAPE
    }

    // 自定义酒枚举
    enum WineEnum implements OrgnizeEnums {
        READ_WINE, SPIRIT
    }
}

class testOrgnizeEnums {
    public static void main(String[] args) {
        OrgnizeEnums.FruitEnum[] fruits = OrgnizeEnums.FruitEnum.values();
        // 遍历数组: fruits
        for (OrgnizeEnums.FruitEnum fruitEnum : OrgnizeEnums.FruitEnum.values()) {
            System.out.println("fruitEnum::" + fruitEnum);
        }

        for (OrgnizeEnums.WineEnum wineEnum : OrgnizeEnums.WineEnum.values()) {
            System.out.println("wineEnum::" + wineEnum);
        }

        EnumSet<FoodEnum> foodEnumSet = EnumSet.allOf(FoodEnum.class);
        for (FoodEnum food : foodEnumSet
                ) {
            System.out.println(food);
        }

        EnumMap<RainBow2Enum, String> map = new EnumMap<RainBow2Enum, String>(RainBow2Enum.class);
        map.put(RainBow2Enum.RED, "red");
        map.put(RainBow2Enum.BLUE, "blue");
        for (Map.Entry<RainBow2Enum, String> entry  : map.entrySet()) {
            System.out.println(entry.getKey().name() + ":" + entry.getValue());
        }
    }

}
