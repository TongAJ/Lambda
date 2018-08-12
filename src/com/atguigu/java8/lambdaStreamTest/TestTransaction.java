package com.atguigu.java8.lambdaStreamTest;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class TestTransaction {
    List<Transaction> transactions = null;

    @Before
    public void before(){
        Trader raoul = new Trader("Raoul","Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brain = new Trader("Brain","Cambridge");

        transactions = Arrays.asList(
                new Transaction(brain,2011,300),
                new Transaction(raoul,2012,1000),
                new Transaction(raoul,2011,400),
                new Transaction(mario,2012,710),
                new Transaction(mario,2012,700),
                new Transaction(alan,2011,950)
        );
    }


    /* 找出2011年发生的所有交易，并按交易额排序（低->高） */
    /* 交易员都在哪些不同的城市工作过 */
    /* 查找所有来自剑桥的交易员，并排序 */
    /* 返回所有交易员的名字字符串，并排序 */
    /* 有没有交易员是在米兰工作的 */
    /* 打印生活在剑桥的所有交易员的交易额 */
    /* 所有的交易额中，最高的交易额是多少 */
    /* 找到最小的交易额 */
    @Test
    public void test01(){
        System.out.println("找出2011年发生的所有交易，并按交易额排序（低->高）:");
        transactions.stream()
                .filter((transaction)-> transaction.getYear()==2011)
                .sorted((x,y) -> Integer.valueOf(y.getValue()).compareTo(Integer.valueOf(y.getValue())))
                .forEach(System.out::println);

        System.out.println();
        System.out.println("交易员都在哪些不同的城市工作过:");
        List<String> cityList = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());
        cityList.forEach(System.out::println);

        System.out.println();
        System.out.println("查找所有来自剑桥的交易员，并排序");
        transactions.stream()
                .filter((x) -> x.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader)
                .sorted(Comparator.comparing(t -> t.getName()))
                .distinct()
                .forEach(System.out::println);

        System.out.println();
        System.out.println("返回所有交易员的名字字符串，并排序");
        transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .sorted()
                .distinct()
                .forEach(System.out::println);

        System.out.println();
        boolean inMilanFlag = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch((t)->t.getCity().equals("Milan"));
        System.out.println("有没有交易员是在米兰工作的:"+inMilanFlag);

        System.out.println();
        System.out.println("打印生活在剑桥的所有交易员的交易额:");
        transactions.stream()
                .filter((t)->t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        System.out.println();
        Optional<Integer> maxValue = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compare);
        System.out.println("所有的交易额中，最大的交易额是多少:"+maxValue.get());
        System.out.println();
        Optional<Transaction> minValue = transactions.stream()
                .min(Comparator.comparing(transaction -> transaction.getValue()));
        System.out.println("所有的交易额中，最小的交易额是多少:"+minValue.get());
    }
}
