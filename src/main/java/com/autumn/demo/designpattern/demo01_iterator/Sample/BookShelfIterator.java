package com.autumn.demo.designpattern.demo01_iterator.Sample;

public class BookShelfIterator implements Iterator {
    // 成员变量: 书架
    private BookShelf bookShelf;
    // 成员变量: 书架的位置
    private int index;
    // 构造函数: 构建一个书架的迭代器, 初始位置0
    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        this.index = 0;
    }

    // 实现Iterator方法: 判断是否书架中是否还有书.
    public boolean hasNext() {
        // 迭代器的位置小于当前的实际容量, 则存在下一个; 否则,没有下一个了
        if (index < bookShelf.getLength()) {
            return true;
        } else {
            return false;
        }
    }

    // 按迭代器的位置取出书架中的书, 并将迭代器指向下一个.
    public Object next() {
        Book book = bookShelf.getBookAt(index);
        index++;
        return book;
    }
}
