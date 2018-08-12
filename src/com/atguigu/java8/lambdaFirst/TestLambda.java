package com.atguigu.java8.lambdaFirst;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestLambda {

    private List<Employee> employees = Arrays.asList(
        new Employee("zhangsan",8,3000),
        new Employee("lisi",18,4000),
        new Employee("wangwu",28,5000),
        new Employee("zhaoliu",38,6000),
        new Employee("tianqi",48,7000)
    );

    /**
     * 需求：比较list中的所有Employee对象，获取年龄大于30的
     */
    @Test
    public void test(){
        /**
         * 方案一：最原始的方式，for循环遍历，判断，返回新的list
         * 缺的：但是当需求改变时（获取工资大于5000、获取姓名等等），需要重新写一个方法
         */
        List<Employee> list1 = getEmployeesByAge(employees);
        List<Employee> list2 = getEmployeesBySalary(employees);
        list1.forEach(System.out::print);
        System.out.println();
        list2.forEach(System.out::print);
        /**
         * 方案二：设计模式：策略模式
         * 创建一个接口，用于比较对象
         * 创建方法，使用策略实现类来判断并遍历出符合条件的list
         * 缺点：为了新的需求，需要创建一个类，但是代码量很少
         */
        List<Employee> list3 = getEmployeesByStrategy(employees,new EmployeeAgeStrategy());
        System.out.println();
        list3.forEach(System.out::print);
        List<Employee> list4 = getEmployeesByStrategy(employees,new EmployeeSalaryStrategy());
        System.out.println();
        list4.forEach(System.out::print);
        /**
         * 为了解决方案二的不足，我们使用匿名内部类来避免创建新的类
         */
        List<Employee> list5 = getEmployeesByStrategy(employees, new MyStrategy<Employee>() {
            @Override
            public boolean compareEmployeeAttr(Employee employee) {
                return employee.getAge()>30;
            }
        });
        System.out.println();
        list5.forEach(System.out::print);
        /**
         * 为了提高匿名内部类的代码的可读性，java8使用了lambda表达式
         */
        List<Employee> list6 = getEmployeesByStrategy(employees,(e) -> e.getSalary()>6000);
        System.out.println();
        list6.forEach(System.out::print);
        /**
         * Java8还有一个新特性就是Stream API,如果只有Employee类，和声明的employees集合的情况下，
         * 如何实现需求。
         */
        System.out.println();
        List<Employee> list7 = employees.stream()
                .filter(employee -> employee.getAge() < 30).collect(Collectors.toList());
        list7.forEach(System.out::print);

    }

    //==============================方案一开始===================================
     public List<Employee> getEmployeesByAge(List<Employee> list){
         List<Employee> employeeList = new ArrayList<>();
         for (Employee employee : list) {
             if(employee.getAge()>30){
                 employeeList.add(employee);
             }
         }
         return employeeList;
     }

    public List<Employee> getEmployeesBySalary(List<Employee> list){
        List<Employee> employeeList = new ArrayList<>();
        for (Employee employee : list) {
            if(employee.getSalary()<5000){
                employeeList.add(employee);
            }
        }
        return employeeList;
    }
    //==============================方案一结束===================================

    //==============================方案二开始===================================
    public List<Employee> getEmployeesByStrategy(List<Employee> list,MyStrategy<Employee> strategy){
        List<Employee> employeeList = new ArrayList<>();
        for (Employee employee : list) {
            if(strategy.compareEmployeeAttr(employee)){
                employeeList.add(employee);
            }
        }
        return employeeList;
    }
    //==============================方案一结束===================================




}
