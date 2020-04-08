package com.example;

import com.example.classloader.MyClassLoader;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        System.out.println(App.class.getClassLoader()); // sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(App.class.getClassLoader().getParent()); // sun.misc.Launcher$ExtClassLoader@1540e19d
        System.out.println(App.class.getClassLoader().getParent().getClass().getClassLoader()); // null

        System.out.println("--------------------------------------");

        MyClassLoader myClassLoader = new MyClassLoader("D:\\FooTest\\threadclassloader\\target\\classes\\java\\lang\\", "random");
        Class c = myClassLoader.loadClass("String1");
        System.out.println("ClassLoader:" + c.getClassLoader());
        Object instance = c.newInstance();
        System.out.println(instance.toString());
    }
}
