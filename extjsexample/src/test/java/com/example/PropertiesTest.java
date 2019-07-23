package com.example;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

public class PropertiesTest {
    public static void main(String[] args) throws Exception{
        Properties pp = new Properties();
        // 类路径下读取
        InputStream is = PropertiesTest.class.getClassLoader().getResourceAsStream("test.properties");
        pp.load(is);
        String aa = pp.getProperty("aaa");
        System.out.println(aa);

        // 任意路径
        BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\Code-Example\\extjsexample\\src\\test\\resources\\test.properties"));
        pp.load(bufferedReader);
        String aaa = pp.getProperty("aaa");
        System.out.println(aaa);

        // 使用Resbundle
        ResourceBundle bundle = ResourceBundle.getBundle("test");
        String aaa1 = bundle.getString("aaa");
        System.out.println(aaa1);
    }
}
