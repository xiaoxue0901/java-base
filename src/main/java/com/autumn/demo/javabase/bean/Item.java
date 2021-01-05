package com.autumn.demo.javabase.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * @author xql132@zcsmart.com
 * @date 2021/1/5
 * @time 15:47
 * @description
 */
@Setter
@Getter
public class Item implements Comparable<Item> {
    private String name;
    private int score;

    public Item(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(Item o) {
        // 按分数排序
        return Integer.compare(this.score, o.score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return score == item.score && Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score);
    }
}
