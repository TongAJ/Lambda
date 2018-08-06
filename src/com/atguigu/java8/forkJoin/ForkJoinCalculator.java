package com.atguigu.java8.forkJoin;

import java.util.concurrent.RecursiveTask;

/**
 * java7---Fork/Join 框架
 * 将任务拆分直至不能拆分为止，将返回结果join之后一起返回
 */
public class ForkJoinCalculator extends RecursiveTask<Long> {
    Long start,end;
    /* 临界值 */
    private static final Long GAP = 10000L;

    public ForkJoinCalculator(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        Long length = end-start;
        //当小于临界值时，直接求和
        if(length<=GAP){
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }else{
            Long middle = (end-start)/2;
            ForkJoinCalculator left = new ForkJoinCalculator(start,middle);
            left.fork();//拆分子任务，压入线程

            ForkJoinCalculator right = new ForkJoinCalculator(middle+1,end);
            right.fork();//拆分子任务，压入线程

            //返回执行结果
            return left.join()+right.join();
        }
    }
}
