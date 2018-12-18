package com.lin.os;

public class MyTest {

    public static void main(String[] args) {
        String dir = "/root/cun/abc";
        String[] split = dir.split("/");
        for (String s : split) {
            System.out.println(s);
        }
    }

}
