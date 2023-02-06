package com.api.quartz;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class QuartzRunner implements ApplicationRunner {

    @Resource
    QuartzScheduler quartzScheduler;

    @Override
    public void run(ApplicationArguments args) {
        quartzScheduler.quart();
    }
}
