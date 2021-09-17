package com.springboot.demo.controller.request;

import com.springboot.demo.enums.UserTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class UserUpdateRequest extends BaseParams {

    @ApiModelProperty(value = "用户名",required = true)
    @NotBlank(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty(value = "用户类型",required = true)
    @NotBlank(message = "用户类型为空")
    private UserTypeEnum UserTypeName;

    @ApiModelProperty(value = "手机号",required = true)
    @NotBlank(message = "手机号不能为空")
    private String userMobile;

    @ApiModelProperty(value = "邮箱",required = true)
    @NotBlank(message = "邮箱不能为空")
    private String userEmail;
}
