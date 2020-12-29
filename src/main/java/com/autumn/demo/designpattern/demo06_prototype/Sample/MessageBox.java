package com.autumn.demo.designpattern.demo06_prototype.Sample;


import com.autumn.demo.designpattern.demo06_prototype.Sample.framework.Product;

public class MessageBox implements Product<MessageBox> {
    private char decochar;
    public MessageBox(char decochar) {
        this.decochar = decochar;
    }
    public void use(String s) {
        int length = s.getBytes().length;
        for (int i = 0; i < length + 4; i++) {
            System.out.print(decochar);
        }
        System.out.println("");
        System.out.println(decochar + " "  + s + " " + decochar);
        for (int i = 0; i < length + 4; i++) {
            System.out.print(decochar);
        }
        System.out.println("");
    }
    public Product createClone() {
        Product p = null;
        try {
            p = (Product)clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return p;
    }


    @Override
    public MessageBox createT() {
        MessageBox m = null;
        try {
            m = (MessageBox) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return m;
    }

    public char getDecochar() {
        return decochar;
    }

    public void setDecochar(char decochar) {
        this.decochar = decochar;
    }
}
