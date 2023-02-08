package com.api.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class QuartzScheduler {
    @Resource
    private Scheduler scheduler;

    public void quart() {
        try {
            System.out.println("项目启动时开启定时任务");
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("myTrigger1", "myGroup1")
                    .withSchedule(CronScheduleBuilder.cronSchedule("*/10 * * * * ?"))
                    .build();
            //创建一个job
            JobDetail job = JobBuilder.newJob(HelloJob.class)
                    .withIdentity("myjob1", "mygroup1").build();
            //注册trigger并启动scheduler
            scheduler.scheduleJob(job, trigger);
        } catch (Exception e) {
            System.out.println("quartz出错!");
        }
    }
}