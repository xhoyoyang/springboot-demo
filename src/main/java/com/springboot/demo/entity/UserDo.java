package com.springboot.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.springboot.demo.enums.UserTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@TableName("user")
@Setter
@Getter
public class UserDo implements Serializable {

    private static final long serialVersionUID = 5371976013477259660L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String userName;

    private String userAccount;

    @TableField("user_type")
    private UserTypeEnum UserTypeName;

    @TableField("user_type")
    private Integer userType;

    private String userMobile;

    private String userEmail;

    private Date createTime;

    private Date updateTime;

    @TableLogic
    private Integer flag;

}
