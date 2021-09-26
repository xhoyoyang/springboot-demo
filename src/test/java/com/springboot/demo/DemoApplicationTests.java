package com.springboot.demo;

import com.springboot.demo.dao.UserMapper;
import com.springboot.demo.entity.User;
import com.springboot.demo.enums.UserTypeEnum;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserMapper userMapper;

    private ThreadPoolExecutor pool = new ThreadPoolExecutor(10,10,0l, TimeUnit.SECONDS,new LinkedBlockingQueue<>());

    @Test
    void contextLoads() {

        System.out.println(new Date());
    }

    @Test
    void redisTest(){
        this.redisTemplate.opsForValue().set("test","test", Duration.ofMinutes(10L));
    }

    @Test
    void passwordTest(){
        String pass = "123qwe";
        System.out.println(DigestUtils.md5Hex(pass));
    }

    @Test
    void createUserTest() throws InterruptedException {
        while (true){
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    User user = new User();
                    user.setUserAccount(new Date().getTime()+"");
                    user.setUserType(UserTypeEnum.admin);
                    user.setUserName(new Date().getTime()+"");
                    user.setUserMobile("15812345678");
                    user.setUserEmail("123@qq.com");
                    userMapper.insert(user);
                }
            });
            Thread.sleep(2);
        }
    }

    @Test
    void updateUserTest(){
        /*UpdateWrapper<UserDo> update = new UpdateWrapper<>();
        update.eq("id",1);
        update.set("user_type",2);
        update.set("create_time",new Date());
        userMapper.update(null,update);*/

        User user = new User();
        //UserDo userDo = this.userMapper.selectById(1);
        //BeanUtils.copyProperties(userDo,user);

        user.setId(1);
        user.setUserType(UserTypeEnum.user);
        this.userMapper.updateById(user);
    }

    @Test
    void deleteUserTest(){

        User user = new User();
        user.setId(7);
        userMapper.deleteById(7);

    }

    @Test
    void insertUsaer(){
        User user = new User();
        user.setUserName("lisi");
        user.setUserAccount("lisi");
        user.setUserType(UserTypeEnum.user);
        user.setUserMobile("15812345678");
        user.setUserEmail("12345@qq.com");
        user.setCreateTime(LocalDateTime.now());
        user.setCreateUser("test");
        //user.setUpdateTime(LocalDateTime.now());
        user.setUpdateUser("test");
        this.userMapper.insert(user);
    }


}
