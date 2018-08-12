package com.atguigu.java8.streamAPI;

import com.atguigu.java8.lambdaFirst.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Steam 操作
 *
 * 生成steam流
 *
 * 中间操作：中间操作对流进行一些列流水线操作，但不会执行，只有到了终止操作
 *          才会一次性全部操作，也称为“惰性操作”
 *
 *          filter：过滤器
 *          limit：限制器
 *          skip：去除器与limit互补
 *          distinct：去重
 *          map：对流中的每一个元素进行操作，返回新的流
 *          flatMap:类似可以理解为Collection的addAll方法，
 *                  如果流中将每个对象转化为一个map或者list
 *                  map--->>{{a,a,a}，{b,b,b}，{c,c,c}}
 *                  flatMap--->>{a,a,a,b,b,b,c,c,c}}
 *          sort(使用类继承的Comparable接口compareTo方法)
 *          自定义sort(使用Camparator<T>接口的compare方法)
 *
 * 终止操作
 *          allMatch---返回boolean，是否全部匹配
 *          anyMatch---返回boolean，是否任意匹配
 *          noneMath---返回boolean，是否全部不匹配
 *          findFirst---寻找第一个
 *          findAny---返回任意
 *          count--计数
 *          max--返回最大值
 *          min--返回最小值
 *          reduce:归约
 *          collection：收集（内涵太多操作）
 */
public class TestStreamAPI {

    private List<Employee> employees = Arrays.asList(
            new Employee("zhangsan",8,3000, Employee.STATUS.FREE),
            new Employee("lisi",18,4000, Employee.STATUS.BUSY),
            new Employee("wangwu",28,5000, Employee.STATUS.VOCATION),
            new Employee("zhaoliu",38,6000, Employee.STATUS.FREE),
            new Employee("tianqi",48,7000, Employee.STATUS.BUSY)
    );

    @Test
    public void testStream(){
        //生成stream流
        employees.stream()
                //中间操作
                //中间放置的打印为了测试惰性操作

                //filter 过滤层
                .filter((employee) -> {
                    //System.out.println("中间操作");
                    return employee.getAge()<40;
                })

                //limit 内置限制，skip与limit互补
                //.limit(2)
                //.skip(1)

                //distinct使用对象的hashcode去比较，所以需要重写equals和hashcode
                .distinct()
                //终止操作
                .forEach(System.out::println);
    }

    @Test
    public void testStreamMap(){
        List<String> list = Arrays.asList("aaa","bbb","ccc");
        list.stream()
                .map(String::toUpperCase)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void testStreamCompare(){
        List<String> list = Arrays.asList("ccc","aaa","bbb");
        list.stream()
//                .sorted()
                //int compare(T o1, T o2); 实现reverse
                .sorted((x,y) -> -x.compareTo(y))
                .forEach(System.out::println);

    }

    /**
     * 使用map时，可以看到打印出来的内容还是stream
     * 因为getSplitList返回的Stream<Character>
     *     还需要去解一层嵌套，类似于Collection.add
     *     这是就可以用FlatMap
     */
    @Test
    public void testStreamFlatMap(){
        List<String> list = Arrays.asList("aaa","bbb","ccc");
        list.stream()
//                .map(TestStreamAPI::getSplitList)
                .flatMap(TestStreamAPI::getSplitList)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void testMatchAndFind(){
        boolean b1 = employees.stream()
                                .allMatch((employee) -> employee.getStatus().equals(Employee.STATUS.FREE));
        System.out.println("b1 = " + b1);
        boolean b2 = employees.stream()
                .anyMatch((employee) -> employee.getStatus().equals(Employee.STATUS.FREE));
        System.out.println("b2 = " + b2);
        boolean b3 = employees.stream()
                .noneMatch((employee) -> employee.getStatus().equals(Employee.STATUS.FREE));
        System.out.println("b3 = " + b3);

        //Optional<T> findFirst();
        Optional<Employee> employee = employees.stream()
                .filter((x) -> x.getAge()>30)
                .findFirst();
        System.out.println(employee.get());

        //.stream 串行
        Optional<Employee> employee2 = employees.stream()
                .filter((x) -> x.getStatus().equals(Employee.STATUS.FREE))
                .findAny();
        System.out.println(employee2.get());
        //。parallelStream 并行
        Optional<Employee> employee3 = employees.parallelStream()
                .filter((x) -> x.getStatus().equals(Employee.STATUS.FREE))
                .findAny();
        System.out.println(employee3.get());
    }

    @Test
    public void testStreamCountAndMaxAndMin(){
        //long count();
        long countNum = employees.stream()
                .filter((employee -> employee.getAge()>20))
                .count();
        System.out.println("countNum = " + countNum);

        //Optional<T>
        Optional<Employee> employeeMaxAge = employees.stream()
                .max(Comparator.comparing(Employee::getAge));
        System.out.println("employeeMaxAge = " + employeeMaxAge.get());

        Optional<Double> employeeMinSalary = employees.stream()
                .map(Employee::getSalary)
                .min(Double::compareTo);
        System.out.println("employeeMinSalary = " + employeeMinSalary.get());

    }

    /* 常用的map-reduce组合 */
    @Test
    public void testStreamReduce(){
        Optional<Double> sumSalary = employees.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println("sumSalary = " + sumSalary);
    }

    /**
     * collect中，习惯使用工具类Collectors完成多种多样的操作
     */
    @Test
    public void testCollection(){
        /* 流的转换Collectors.toList()、Collectors.toSet()、Collectors.toMap()  */
        List<String> employeeList =employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        employeeList.forEach(System.out::println);

        System.out.println("============================");

        Set<Employee> employeeSet =employees.stream()
                .collect(Collectors.toSet());
        employeeSet.forEach(System.out::println);

        System.out.println("============================");
        /* 自定义集合 */
        HashSet<String> hashSetString =  employees.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        hashSetString.forEach(System.out::println);

        //======================转集合结束====================================

        //======================总数、平均值=================================

        System.out.println("============================");
        Double averageSalary = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println("averageSalary" + averageSalary);

        Double totalSalary = employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println("totalSalary" + totalSalary);

        //======================分组================================

        System.out.println("============================");
        Map<Employee.STATUS,List<Employee>> statusListMap = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(statusListMap);

        /* 多重分组 */
        System.out.println("============================");
        Map<Employee.STATUS,Map<String,List<Employee>>> statusListMap2 = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus,Collectors.groupingBy((e) -> {
                    if(e.getAge()<=30){
                        return "青年";
                    }else if(e.getAge()<=40){
                        return "中年年";
                    }else{
                        return "老年";
                    }
                })));
        System.out.println(statusListMap2);

        /* 满足条件的和不满足条件的 */
        System.out.println("============================");
        Map<Boolean,List<Employee>> empBooleanListMap = employees.stream()
                .collect(Collectors.partitioningBy((e)->e.getSalary()>5000));
        System.out.println("empBooleanListMap = " + empBooleanListMap);

        /* 连接功能 */
        System.out.println("============================");
        String joinName = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(","));
        System.out.println("joinName = " + joinName);
    }

    //=====辅助方法===============================================
    private static Stream<Character> getSplitList(String str){
        List<Character> list = new ArrayList<>();
        for (char c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }


}
