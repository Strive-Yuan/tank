package com.api.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;

public class HelloJob extends QuartzJobBean {
    public HelloJob() {
    }

//    @Override
//    public void execute(JobExecutionContext context) {
////        Object tv1 = context.getTrigger().getJobDataMap().get("t1");
////        System.out.println(tv1);
////        Object tv2 = context.getTrigger().getJobDataMap().get("t2");
////        System.out.println(tv2);
////        Object jv1 = context.getJobDetail().getJobDataMap().get("j1");
////        System.out.println(jv1);
////        Object jv2 = context.getJobDetail().getJobDataMap().get("j2");
////        System.out.println(jv2);
//        System.out.println("我是测试程序，难道要我一直跑吗？:" + LocalDateTime.now());
//    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("我是测试程序，难道要我一直跑吗？:" + LocalDateTime.now());
    }

}