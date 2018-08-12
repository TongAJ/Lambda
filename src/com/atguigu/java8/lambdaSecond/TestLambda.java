package com.atguigu.java8.lambdaSecond;

import com.atguigu.java8.lambdaFirst.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * 能够使用
 *
 */
public class TestLambda {

    private List<Employee> employees = Arrays.asList(
            new Employee("zhangsan",8,3000),
            new Employee("lisi",18,4000),
            new Employee("wangwu",28,5000),
            new Employee("zhaoliu",38,6000),
            new Employee("tianqi",38,7000)
    );

    //使用Collection.sort方法，自定义comparetor排序方法，
    //按年龄排序，年龄相同按名字
    @Test
    public void testLambda01(){
        //public static <T> void sort(List<T> list, Comparator<? super T> c) {
        Collections.sort(employees,(e1,e2) -> {
            if(e1.getAge()==e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else{
                return e1.getAge().compareTo(e2.getAge());
            }
        });
        for (Employee employee : employees) {
            System.out.println("employee = " + employee);
        }
    }

    //需求：将接收到的Stirng。以大写的形式返回
    @Test
    public void testLambda02(){
        String qwerty = getUpperCase("qwerty",(string)->string.toUpperCase());
        System.out.println("new qwerty = " + qwerty);
    }

    public String getUpperCase(String string,MyFunction myFunction){
        String upperCase = myFunction.getValue(string);
        return upperCase;
    }

    //需求：创建一个函数式接口，泛型T,R t为入参，R返回类型
    //对两个Long类型的数据，求他们的乘积
    @Test
    public void testLambda03(){
        Long resutl = getMulti(100L,200L,(l1,l2) -> l1*l2);
        System.out.println("resutl = " + resutl);
    }

    public Long getMulti(Long l1,Long l2,MyFunction02<Long,Long> myFunction){
        return myFunction.getMulti(l1,l2);
    }
}
