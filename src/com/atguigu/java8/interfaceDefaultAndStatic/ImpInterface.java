package com.atguigu.java8.interfaceDefaultAndStatic;

public class ImpInterface implements MyInterface01,MyInterface02 {

    /**
     * 当同时实现两个接口，并且两个接口都有相同Default方法时，必须实现或者调用其中一个
     * @return
     */
    @Override
    public String getName() {
//        return "ImpInterface.getName...";
        return MyInterface01.super.getName();
    }
}
