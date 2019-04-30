package com.autumn.demo.javabase.designpattern.demo01_iterator.Sample;

public class BookShelf implements Aggregate {
    // 成员变量: 定义一个数组,数组中存放Book
    private Book[] books;
    // 代表数组实际的存储量.
    private int last = 0;

    // 构造函数: 创建BookShelf对象, 要确认书架的容量/大小.
    public BookShelf(int maxsize) {
        this.books = new Book[maxsize];
    }

    // 根据位置取出相应的书
    public Book getBookAt(int index) {
        return books[index];
    }

    // 向书架上放书. 将book存入数组中
    public void appendBook(Book book) {
        this.books[last] = book;
        last++;
    }

    // 获取目前的存书量
    public int getLength() {
        return last;
    }

    // 实现Aggregate的方法: 创建一个书架的迭代器
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
}
