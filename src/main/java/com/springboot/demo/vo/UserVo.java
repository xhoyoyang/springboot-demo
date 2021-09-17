package com.springboot.demo.vo;

import com.springboot.demo.annotation.Desensitization;
import com.springboot.demo.enums.DesensitizationType;
import com.springboot.demo.enums.UserTypeEnum;
import com.springboot.demo.vo.common.BaseVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter
@Getter
@ToString
public class UserVo extends BaseVo {

    private String userName;

    private String userAccount;

    private UserTypeEnum UserTypeName;

    private Integer userType;

    @Desensitization(type = DesensitizationType.MOBILE_PHONE)
    private String userMobile;

    @Desensitization(type = DesensitizationType.EMAIL)
    private String userEmail;

    private Date createTime;

    private Date updateTime;

    private Integer flag;

}
