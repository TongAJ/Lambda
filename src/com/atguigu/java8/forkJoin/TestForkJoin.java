package com.atguigu.java8.forkJoin;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class TestForkJoin {

    @Test
    public void test01(){
        //java8新特性，时间戳
        Instant start = Instant.now();

        //创建pool
        ForkJoinPool pool = new ForkJoinPool();
        //创建Task
        ForkJoinTask<Long> calculator = new ForkJoinCalculator(0L,1000000000L);
        //加入到池中
        pool.invoke(calculator);

        Instant end = Instant.now();

        /* Duration.between 处理Instant的差值 */
        Long time = Duration.between(end,start).toMillis();
        System.out.println("消耗时间："+time);
    }
}
