package com.atguigu.java8.funcitonInterface;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * JAVA8的四大内置函数式接口
 *
 * Consumer<T>: void accept(T t);
 *
 * Supplier<T>: T get();
 *
 * Function<T,R> : R apply(T t);
 *
 * Predicate<T>: boolean test(T t);
 *
 * 还有更多的子接口提供更多的函数式接口
 */
public class TestLambda {

    @Test
    public void testConsumer(){
        consumerFun(100,(integer) -> System.out.println("消费了"+integer+"元"));
    }

    @Test
    public void testSupplier(){
        System.out.println(supplierFun(() -> 100));
    }
    
    @Test
    public void testFunction(){
        String newString = functionFun("asdasdasd",(string) -> string.toUpperCase());
        System.out.println("newString = " + newString);
    }

    @Test
    public void testPredicate(){
        boolean flag = predicateFun(100,(integer -> {
            if(integer>50){
                return true;
            }else{
                return false;
            }
        }));
        System.out.println("flag = " + flag);
    }

    //==============================内置接口赋值方法======================================
    public void consumerFun(Integer integer, Consumer<Integer> consumer){
        consumer.accept(integer);
    }
    public Integer supplierFun(Supplier<Integer> supplier){
        return supplier.get();
    }
    public String functionFun(String string, Function<String,String> function){
        return function.apply(string);
    }
    public boolean predicateFun(Integer integer, Predicate<Integer> predicate){
        return predicate.test(integer);
    }
}
