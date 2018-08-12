package com.atguigu.java8.lambdaSecond;

@FunctionalInterface
public interface MyFunction02<T,R> {
    R getMulti(T t1,T t2);
}
