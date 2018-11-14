package com.autumn.demo.javabase.designpattern.demo06_prototype.Sample.framework;
import com.autumn.demo.javabase.designpattern.demo06_prototype.Sample.deepclone.Person;
import com.autumn.demo.javabase.designpattern.demo06_prototype.Sample.deepclone.Product2;

import java.io.*;
import java.util.*;

public class Manager {
    private HashMap showcase = new HashMap();
    private HashMap<String, Person> deep = new HashMap();
    public void register(String name, Product proto) {
        showcase.put(name, proto);
    }
    public Product create(String protoname) {
        Product p = (Product)showcase.get(protoname);
        return p.createClone();
    }

    public void register(String name, Person proto) {
        deep.put(name, proto);
    }

    public Person createDeep(String name) {
        Person p = deep.get(name);
        return p.createClone();
    }

    public static <T> T createDeepObj(T obj) throws IOException, ClassNotFoundException {
     // 先写对象
       ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bout);
        oos.writeObject(obj);

        // 再读对象
        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bin);
        return (T) ois.readObject();
    }
}
