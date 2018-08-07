package com.atguigu.java8.newTimeApi;

import org.junit.Test;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

public class TestLocalDateTime {
    /**
     * LocalDate--日期
     * LocalTime--时间
     * LocalDateTime--日期和时间
     */
    @Test
    public void testLocalDateTime(){
        //now方法获取一个新的实例
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime);

        //of(int year, int month, int dayOfMonth, int hour, int minute, int second)
        //创建新的实例
        LocalDateTime localDateTime1 = LocalDateTime.of(2018,8,7,14,51, 0);
        System.out.println("localDateTime1 = " + localDateTime1);

        System.out.println(localDateTime1.getYear());
        System.out.println(localDateTime1.getMonthValue());
        System.out.println(localDateTime1.getDayOfMonth());
        System.out.println(localDateTime1.getDayOfWeek());
        System.out.println(localDateTime1.getDayOfYear());

        localDateTime1.plusYears(1);
        //可以看到即使year+1，localDateTime1还是本身
        System.out.println(localDateTime1);
        //新的实例，验证了LocalDateTime是不可变类
        //修改日期或者时间可以使用plus/minus
        LocalDateTime localDateTime2 = localDateTime1.plusYears(1).minusMonths(1);
        System.out.println(localDateTime2);
    }

    /**
     * Instant
     *      带偏移量的时间戳--以距离Unix元年(1970-01-01-00:00:00)为准
     *
     */
    @Test
    public void testInstant(){
        //now获取Instant对象
        Instant instant = Instant.now();
        //Instant本身采用的是UTC时区，和我们有8小时
        System.out.println(instant.toString());
        //偏移
        ZoneOffset zoneOffset = ZoneOffset.ofHours(8);
        //Instant添加了偏移量 返回OffsetDateTime
        OffsetDateTime offsetDateTime = instant.atOffset(zoneOffset);
        System.out.println(offsetDateTime);

    }

    /**
     * Duration
     *      时间差
     */
    @Test
    public void testDuration(){
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = LocalDateTime.of(2015,1,1,0,0,0);
        Duration duration = Duration.between(localDateTime1,localDateTime);
        //PT31553H20M7.81S  31553小时又20分钟又7.81秒
        System.out.println(duration.toString());
        System.out.println(duration.toHours());
        System.out.println(duration.toDays());
        System.out.println(duration.toMinutes());
    }

    /**
     * Period
     *      日期差
     */
    @Test
    public void testPeriod(){
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        LocalDate localDate1 = localDate.plusYears(2);
        Period period = Period.between(localDate,localDate1);
        //P2Y
        System.out.println(period.toString());
        //2
        System.out.println(period.getYears());
    }
    /**
     * TemporalAdjuster
     *      时间矫正器
     */
    @Test
    public void testAdjuster(){
        LocalDateTime localDateTime = LocalDateTime.now();
        //获取当前日期-月的第x天
        LocalDateTime localDateTime1 = localDateTime.withDayOfMonth(2);
        System.out.println(localDateTime1);
        //获取当前日期-年的第x天
        LocalDateTime localDateTime2 = localDateTime.withDayOfYear(3);
        System.out.println(localDateTime2);
        //获取当前时间-分钟的第二分钟
        LocalDateTime localDateTime3 =localDateTime.withMinute(2);
        System.out.println(localDateTime3);

        //TemporalAdjusters辅助工具
        //firstDayOfMonth很直观
        LocalDateTime localDateTime4 = localDateTime.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(localDateTime4);

        //由于TemporalAdjuster是一个函数式接口
        //也可以通过lambda表达式自定义矫正器
        //Temporal adjustInto(Temporal temporal);
        LocalDateTime localDateTime5 = localDateTime.with((l)->{
            //Temporal是个接口，正巧的是LocalDateTime是他的实现类
            LocalDateTime ldt = (LocalDateTime)l;
            if(ldt.getDayOfWeek().equals(DayOfWeek.FRIDAY)){
                return ldt.plusDays(3);
            }else if(ldt.getDayOfWeek().equals(DayOfWeek.SATURDAY)){
                return ldt.plusDays(2);
            }else if(ldt.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
                return ldt.plusDays(1);
            }else
                return ldt;
        });
        System.out.println(localDateTime5);
    }

    /**
     * DateTimeFormatter
     */
    @Test
    public void testDateTimeFormatter(){

    }

    /**
     * ZoneDate,ZoneTime,ZoneDateTime
     */
    @Test
    public void testZone(){

    }
}
