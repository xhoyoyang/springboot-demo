package com.springboot.demo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.springboot.demo.util.AuthorizationUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


/**
 * mybatis自动填充配置
 */
@Slf4j
@Component
public class FillMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        // 起始版本 3.3.0(推荐使用)
        this.strictInsertFill(metaObject, "createUser", String.class, AuthorizationUtil.currentUser() == null ? "system" : AuthorizationUtil.currentUser().getUserAccount());
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        // 起始版本 3.3.0(推荐)
        this.strictInsertFill(metaObject, "updateUser", String.class, AuthorizationUtil.currentUser() == null ? "system" : AuthorizationUtil.currentUser().getUserAccount());
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }
}
