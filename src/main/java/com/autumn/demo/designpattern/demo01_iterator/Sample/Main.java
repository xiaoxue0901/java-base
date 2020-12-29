package com.autumn.demo.designpattern.demo01_iterator.Sample;

public class Main {
    public static void main(String[] args) {
        // 创建一个能容纳4本书的书架
        BookShelf bookShelf = new BookShelf(4);
        // 向书架中加书
        bookShelf.appendBook(new Book("Around the World in 80 Days"));
        bookShelf.appendBook(new Book("Bible"));
        bookShelf.appendBook(new Book("Cinderella"));
        bookShelf.appendBook(new Book("Daddy-Long-Legs"));
        // 构建书架的迭代器(通过BookShelfIterator实现: 构造函数将收到的BookShelf实例保存在bookShelf字段中, 将index初始化为0)
        Iterator it = bookShelf.iterator();
        // hasNext判断当书架中还有书时,使用next按顺序取出书架中的书.
        while (it.hasNext()) {
            Book book = (Book)it.next();
            System.out.println(book.getName());
        }
    }
}
