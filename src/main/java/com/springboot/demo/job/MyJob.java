package com.springboot.demo.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class MyJob extends QuartzJobBean {

    /*public class FirstJob extends QuartzJobBean {
        @Override
        protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            String now = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
            log.info("FirstJob执行，当前的时间: " + now);
        }
    }

    public class SecondJob extends QuartzJobBean {

        @Override
        protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            String now = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
            log.info("SecondJob执行, 当前的时间: " + now);
        }
    }*/

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String now = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        log.info("FirstJob执行，当前的时间: " + now);
    }
}
