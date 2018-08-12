package com.atguigu.java8.interfaceDefaultAndStatic;

public class TestInterface {
    public static void main(String[] args) {
        ImpInterface impInterface = new ImpInterface();
        System.out.println(impInterface.getName());
        System.out.println(MyInterface02.getStaticName());
    }
}
