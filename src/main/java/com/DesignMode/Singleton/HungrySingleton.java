package com.DesignMode.Singleton;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

/**
 * 饿汉式
 * 第一种写法
 * 类加载到内存后，就实例化一个单例，JVM保证线程安全
 * 简单实用
 * 唯一的缺点：不管是否用到，类加载时就完成实例化（不管是否用到，只要类一加载类就会初始化）
 */
public class HungrySingleton {

    private static final HungrySingleton hungrySingleton = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return hungrySingleton;
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        LocalDateTime localDateTime = LocalDate.now().atTime(LocalTime.MIN).plusDays(-1);
        System.out.println(localDateTime);
//        LocalDateTime localDateTime = LocalDateTime.of(2022, 12, 29, 0, 0, 0);
        Date start = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        String startDate = sdf.format(start);
        LocalDateTime localDateTimeEnd = LocalDate.now().atTime(LocalTime.MAX).plusDays(-1);
//        LocalDateTime localDateTimeEnd = LocalDateTime.of(2022, 12, 29, 23, 59, 59);
        System.out.println(localDateTimeEnd);
        Date end = Date.from(localDateTimeEnd.atZone(ZoneId.systemDefault()).toInstant());
        String endDate = sdf.format(end);
        System.out.println(startDate);
        System.out.println(endDate);
    }
}
