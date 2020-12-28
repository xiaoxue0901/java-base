package com.autumn.demo.javabase.enumerate.advanced;

/**
 * @author xql132@zcsmart.com
 * @date 2019/1/14 17:25
 * @description 枚举用法升级, 加自定义方法
 */
public enum RainBow2Enum implements Color {
    /**
     * 彩虹颜色枚举
     */
    RED(0, "彩虹-红色"),
    ORANGE(1, "彩虹-橙色"),
    YELLEOW(2, "彩虹-黄色"),
    GREEN(3, "彩虹-绿色"),
    BLUE(4, "彩虹-蓝色");

    RainBow2Enum(int flag, String name) {
        this.flag = flag;
        this.name = name;
    }

    private int flag;
    private String name;

    @Override
    public String getRainBowColor() {
        System.out.println("彩虹的标记是::" + flag + "颜色是::" + name);
        return name;
    }

}
