package com.springboot.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.springboot.demo.common.entity.BaseEntity;
import com.springboot.demo.common.enums.UserTypeEnum;
import lombok.Data;

@TableName("user")
@Data
public class User extends BaseEntity {

    private String userName;

    private String userAccount;

    @TableField("user_type")
    private UserTypeEnum userType;

    private String userMobile;

    private String userEmail;

    private String userPassword;


}
