package com.atguigu.java8.interfaceDefaultAndStatic;

public interface MyInterface02 {
    default String getName(){
        return "MyInterface02";
    }

    static String getStaticName(){
        return "getStaticName";
    }
}
