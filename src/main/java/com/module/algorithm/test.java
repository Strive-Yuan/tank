package com.module.algorithm;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.TimeZone;

public class test {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        LocalDateTime localDateTime = LocalDate.of(2022,12,13).atTime(LocalTime.MIN).plusDays(-1);
        System.out.println(localDateTime.atZone(ZoneId.systemDefault()).toInstant());


        LocalDateTime localDateTime1 = LocalDate.of(2022,12,13).atTime(LocalTime.MAX).plusDays(-1);
        System.out.println(localDateTime1.atZone(ZoneId.systemDefault()).toInstant());



        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        sdf.setTimeZone(TimeZone.getTimeZone("CST"));
        LocalDateTime localDateTime2 =LocalDateTime.of(2022,12,12,13,31,14);
        System.out.println(localDateTime2.atZone(ZoneId.systemDefault()).toInstant());
    }


}
