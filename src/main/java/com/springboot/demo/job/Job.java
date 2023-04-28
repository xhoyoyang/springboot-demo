package com.springboot.demo.job;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import com.springboot.demo.common.enums.UserTypeEnum;
import com.springboot.demo.dao.TestUserMapper;
import com.springboot.demo.entity.TestUser;
import com.springboot.demo.ws.WebSocketHandler;
import com.xxl.job.core.context.XxlJobContext;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by huiyang on 2022-07-21
 */
@Component
@Slf4j
public class Job {


    @Resource
    private TestUserMapper testUserMapper;

    @Resource
    private Redisson redisson;

    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("demoJobHandler")
    public void demoJobHandler() throws Exception {

        RLock lock = redisson.getLock("auth:test:user:create");
        long start = System.currentTimeMillis();
        try {

            lock.lock();
            int count = this.testUserMapper.count();
            if(count > 2000*10000){
                XxlJobHelper.log("XXL-JOB, databases test_user is done");
                lock.unlock();
                return;
            }
            lock.unlock();
            XxlJobHelper.log("XXL-JOB, start create test_user");

            for (int i = 0; i < 100; i++) {
                String uuid = IdUtil.fastUUID();
                TestUser testUser = TestUser.builder().userName(uuid).userAccount(uuid).userType(UserTypeEnum.user).userMobile("13112345678")
                        .userEmail(uuid + "@qq.com").userPassword(uuid).build();
                testUser.setDeleted(0);
                this.testUserMapper.insert(testUser);
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            log.info("XXL-JOB, stop create test_user, cust time :{} S",(System.currentTimeMillis()-start)/100);
            XxlJobHelper.log("XXL-JOB, stop create test_user, cust time :{} S",(System.currentTimeMillis()-start)/100);
        }
    }


}
