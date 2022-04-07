package com.springboot.demo.job;

import com.springboot.demo.dao.UserMapper;
import com.springboot.demo.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * DisallowConcurrentExecution:是否并发执行，系统默认为true,即第一个任务还未执行完整，第二个任务如果到了执行时间，则会立马开启新线程执行任务
 */
@Slf4j
@DisallowConcurrentExecution
public class MyJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String now = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        UserMapper mapper = SpringUtil.getBean(UserMapper.class);
        log.info("开始执行myJob: {}", mapper.selectById(1).toString());
//        log.info("开始执行myJob: {}", now);
    }
}
