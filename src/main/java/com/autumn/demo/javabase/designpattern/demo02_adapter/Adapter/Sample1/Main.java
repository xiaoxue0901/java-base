package com.autumn.demo.javabase.designpattern.demo02_adapter.Adapter.Sample1;

public class Main {
    public static void main(String[] args) {
        // 使用Print接口编程. Banner类及其方法被隐藏了.
        // 通过将对象保存在Print类型的变量中并使用改变量, 可以明确的表明程序的意图, 即"并不是使用PrintBanner类中的方法, 而是使用Print接口中的方法"
        Print p = new PrintBanner("Hello");
        // 使用的是Print接口的方法. 可以在不对main类修改的情况下改变PrintBanner类的具体实现
        p.printWeak();
        p.printStrong();
    }
}
