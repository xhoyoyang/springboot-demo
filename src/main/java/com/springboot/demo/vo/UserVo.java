package com.springboot.demo.vo;

import com.springboot.demo.annotation.Desensitization;
import com.springboot.demo.common.enums.DesensitizationType;
import com.springboot.demo.common.enums.UserTypeEnum;
import com.springboot.demo.common.entity.BaseVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class UserVo extends BaseVo {

    private String userName;

    private String userAccount;

    private UserTypeEnum userType;

    @Desensitization(type = DesensitizationType.MOBILE_PHONE)
    private String userMobile;

    @Desensitization(type = DesensitizationType.EMAIL)
    private String userEmail;

    private List<String> roleNames;

    private List<Integer> roles;

}
