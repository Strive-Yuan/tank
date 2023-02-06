package com.api.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

@Component
public class QuartzScheduler {

    public void quart() {
        try {
            System.out.println("项目启动时开启定时任务");
            //创建一个scheduler
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            //创建一个Trigger
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("myTrigger1", "myGroup1")
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3)
                            .repeatForever()).build();
            //创建一个job
            JobDetail job = JobBuilder.newJob(HelloJob.class)
                    .withIdentity("myjob1", "mygroup1").build();
            //注册trigger并启动scheduler
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        } catch (Exception e) {
            System.out.println("quartz出错!");
        }
    }
}