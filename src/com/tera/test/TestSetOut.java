package com.tera.test;

import java.io.PrintStream;

public class TestSetOut {
    public static void main(String args[]) {
        try {
            PrintStream out = System.out;
            PrintStream ps = new PrintStream("log.txt");
            System.setOut(ps);
            System.out.print("----------------");
            System.out.print("hello world");
            System.setOut(out);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
