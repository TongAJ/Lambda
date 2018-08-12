package com.atguigu.java8.lambdaStreamTest;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ExcersiceStream {
    List<Integer> list01 = Arrays.asList(1,2,3,4,5);
    /**
     * 给定一个列别，返回每一个的平法
     * [1,2,3,4,5]
     */
    @Test
    public void test01(){
        list01.stream()
                .map((integer)->integer*integer)
                .forEach(System.out::println);
    }




}
