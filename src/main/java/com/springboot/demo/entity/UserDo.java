package com.springboot.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.springboot.demo.annotation.Desensitization;
import com.springboot.demo.enums.DesensitizationType;
import com.springboot.demo.enums.UserTypeEnum;

import java.io.Serializable;
import java.util.Date;

@TableName("user")
public class UserDo implements Serializable {

    private static final long serialVersionUID = 5371976013477259660L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @Desensitization(type = DesensitizationType.CHINESE_NAME)
    private String userName;

    private String userAccount;

    @TableField("user_type")
    private UserTypeEnum UserTypeName;

    @TableField("user_type")
    private Integer userType;

    @Desensitization(type = DesensitizationType.MOBILE_PHONE)
    private String userMobile;

    @Desensitization(type = DesensitizationType.EMAIL)
    private String userEmail;

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

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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
