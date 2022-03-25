package com.springboot.demo.config;

import com.springboot.demo.job.MyJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class QuartzConfig {

    private static final String ID = "SUMMERDAY";

    @Bean
    public JobDetail myJobDetail() {
        return JobBuilder.newJob(MyJob.class)
                .withIdentity(ID + " 01")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger myTrigger() {
        // 简单的调度计划的构造器
        //SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever();
        //corn调度器
        //CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ? *");

        return TriggerBuilder.newTrigger()
                .forJob(myJobDetail())
                .withIdentity(ID + " 01Trigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? *"))
                .startNow()
                .build();
    }
}
