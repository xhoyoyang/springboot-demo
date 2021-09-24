package com.springboot.demo.controller.request;

import com.springboot.demo.entity.Role;
import com.springboot.demo.enums.UserTypeEnum;
import com.springboot.demo.validate.Create;
import com.springboot.demo.validate.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Setter
@Getter
public class UserRequest extends BaseParams {

    @ApiModelProperty(value = "账号",required = true)
    @NotBlank(message = "账号不能为空",groups = {Create.class})
    private String userAccount;

    @ApiModelProperty(value = "用户名",required = true)
    @NotBlank(message = "用户名不能为空",groups = {Create.class, Update.class})
    private String userName;

    @ApiModelProperty(value = "用户类型",required = true)
    @NotBlank(message = "用户类型为空",groups = {Create.class, Update.class})
    private UserTypeEnum UserTypeName;

    @ApiModelProperty(value = "手机号",required = true)
    @NotBlank(message = "手机号不能为空",groups = {Create.class, Update.class})
    @Pattern(regexp = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$",message = "手机号码有误！",groups = {Create.class, Update.class})
    private String userMobile;

    @ApiModelProperty(value = "邮箱",required = true)
    @NotBlank(message = "邮箱不能为空",groups = {Create.class, Update.class})
    private String userEmail;

    @ApiModelProperty("角色")
    @NotEmpty(message = "角色不能为空",groups = {Create.class, Update.class})
    private List<Role> roles;
}
