package com.example.thread_demo.chap3;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipFile;

/**
 * 一个生产者和一个消费者操作集合
 *
 */
public class Demo11 {
    public static void main(String[] args) throws IOException {

        File aaaa = File.createTempFile("aaaa", ".tmp");
        System.out.println(aaaa.getAbsolutePath());
    }
}
