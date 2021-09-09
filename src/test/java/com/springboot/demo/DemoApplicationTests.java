package com.springboot.demo;

import com.springboot.demo.dao.UserMapper;
import com.springboot.demo.entity.UserDo;
import com.springboot.demo.enums.UserTypeEnum;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.util.Date;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserMapper userMapper;

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
    void createUserTest(){
        UserDo user = new UserDo();
        user.setUserAccount(DigestUtils.md5Hex(RandomUtils.nextInt()+""));
        user.setUserTypeName(UserTypeEnum.admin);
        userMapper.insert(user);
    }

    @Test
    void updateUserTest(){
        /*UpdateWrapper<UserDo> update = new UpdateWrapper<>();
        update.eq("id",1);
        update.set("user_type",2);
        update.set("create_time",new Date());
        userMapper.update(null,update);*/

        UserDo user = new UserDo();
        //UserDo userDo = this.userMapper.selectById(1);
        //BeanUtils.copyProperties(userDo,user);

        user.setId(1);
        user.setUserType(1);
        this.userMapper.updateById(user);
    }

}
