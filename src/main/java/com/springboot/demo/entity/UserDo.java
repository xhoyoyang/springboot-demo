package com.springboot.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.springboot.demo.enums.UserTypeEnum;

import java.io.Serializable;
import java.util.Date;

@TableName("user")
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

    private Date createTime;

    private Date updateTime;

    @TableLogic
    private Integer flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public UserTypeEnum getUserTypeName() {
        return UserTypeName;
    }

    public void setUserTypeName(UserTypeEnum userTypeName) {
        UserTypeName = userTypeName;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
