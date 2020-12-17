package com.autumn.demo.javabase.designpattern.demo01_iterator.A1;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class Main {
    public static void main(String[] args) {
//        BookShelf bookShelf = new BookShelf(4);
//        bookShelf.appendBook(new Book("Around the World in 80 Days"));
//        bookShelf.appendBook(new Book("Bible"));
//        bookShelf.appendBook(new Book("Cinderella"));
//        bookShelf.appendBook(new Book("Daddy-Long-Legs"));
//        bookShelf.appendBook(new Book("East of Eden"));
//        bookShelf.appendBook(new Book("Frankenstein"));
//        bookShelf.appendBook(new Book("Gulliver's Travels"));
//        bookShelf.appendBook(new Book("Hamlet"));
//        Iterator it = bookShelf.iterator();
//        while (it.hasNext()) {
//            Book book = (Book)it.next();
//            System.out.println(book.getName());
//        }
        test();
    }

    public static void test() {
        Map<String,String> map = new HashMap<>();
        map.put("1", "A");
        map.put("2", "B");
        map.put("3", "C");
        System.out.println("json "+ JSON.toJSONString(map));

    }
}
