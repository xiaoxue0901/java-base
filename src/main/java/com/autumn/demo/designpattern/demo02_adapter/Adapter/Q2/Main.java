package com.autumn.demo.designpattern.demo02_adapter.Adapter.Q2;

import com.autumn.demo.designpattern.demo02_adapter.Adapter.A2.FileIO;
import com.autumn.demo.designpattern.demo02_adapter.Adapter.A2.FileProperties;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        FileIO f = new FileProperties();
        try {
            f.readFromFile("file.txt");
            f.setValue("year", "2004");
            f.setValue("month", "4");
            f.setValue("day", "21");
            f.writeToFile("newfile.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
