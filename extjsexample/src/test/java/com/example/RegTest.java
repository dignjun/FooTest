package com.example;

public class RegTest {

    public static void main(String[] args) {
        String str = "   abc    2423          ada   ";
        String r = str.replaceAll("\\b\\s+\\b", " ");
        System.out.println(r);
    }
}
