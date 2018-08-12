package com.atguigu.java8.newTimeApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TestTime {
    public static void main(String[] args) throws Exception  {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        //使用Lambda表达式创建一个Callable线程
        Callable<LocalDate> task = ()->LocalDate.parse("20180807",dtf);

        //创建指定个数的线程池
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<LocalDate>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            //将线程的执行结果加入到results中
            results.add(pool.submit(task));
        }

        //通过输出可以看出LocalDate是线程安全的，每个对象都是新new出来的，LocalDate为不可变类
        for (Future<LocalDate> result : results) {
            System.out.println("result= " + result.get());
        }

        pool.shutdown();
    }

}
