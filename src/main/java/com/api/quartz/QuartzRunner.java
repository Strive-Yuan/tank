package com.api.quartz;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class QuartzRunner implements CommandLineRunner {

    @Resource
    QuartzScheduler quartzScheduler;

    @Override
    public void run(String... args) throws Exception {
        quartzScheduler.quart();
    }
}
