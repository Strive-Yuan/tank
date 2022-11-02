package DesignMode.factorymethod;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Car implements Vehicle {

    @Override
    public void run() {
        System.out.println("开着跑车秋名山漂移~");
    }


    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        LocalDateTime localDateTime = LocalDateTime.of(2022, 10, 31, 0, 0, 0);
        Date start = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        String startDate = sdf.format(start);
        System.out.println(startDate);
        LocalDateTime localDateTimeEnd = LocalDateTime.of(2022, 10, 31, 23, 59, 59);
        Date end = Date.from(localDateTimeEnd.atZone(ZoneId.systemDefault()).toInstant());
        String endDate = sdf.format(end);
        System.out.println(endDate);

                //"start_date" : "2022-10-30T16:00:00+0000",
                //"end_date"   : "2022-10-31T15:23:23+0000",


    }
}
