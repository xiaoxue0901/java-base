package com.autumn.demo.designpattern.demo02_adapter.Adapter.Sample2;

public class Main {
    public static void main(String[] args) {
        Print p = new PrintBanner("Hello");
        p.printWeak();
        p.printStrong();
    }
}
