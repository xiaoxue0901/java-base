package com.autumn.demo.javabase.designpattern.demo06_prototype.Sample.framework;
import java.lang.Cloneable;

public interface Product<T> extends Cloneable {
    public abstract void use(String s);
    public abstract Product createClone();
    public abstract T createT();


}
