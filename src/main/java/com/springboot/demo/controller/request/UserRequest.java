package com.springboot.demo.controller.request;

import com.springboot.demo.common.enums.UserTypeEnum;
import com.springboot.demo.validate.group.Create;
import com.springboot.demo.validate.group.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.*;
import java.util.List;

@Data
@ToString
public class UserRequest {

    @ApiModelProperty(value = "id,修改时不能为空",required = true)
    @NotNull(message = "",groups = {Update.class})
    private Integer id;

    @ApiModelProperty(value = "账号",required = true)
    @NotBlank(message = "账号不能为空",groups = {Create.class})
    @Null(message = "修改用户时，账号必须为空",groups = Update.class)
    private String userAccount;

    @ApiModelProperty(value = "用户名",required = true)
    @NotBlank(message = "用户名不能为空",groups = {Create.class, Update.class})
    private String userName;

    @ApiModelProperty(value = "用户类型",required = true)
    @NotNull(message = "用户类型为空",groups = {Create.class, Update.class})
    private UserTypeEnum userType;

    @ApiModelProperty(value = "手机号",required = true)
    @NotBlank(message = "手机号不能为空",groups = {Create.class, Update.class})
    @Pattern(regexp = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$",message = "手机号码有误！",groups = {Create.class, Update.class})
    private String userMobile;

    @ApiModelProperty(value = "邮箱",required = true)
    @NotBlank(message = "邮箱不能为空",groups = {Create.class, Update.class})
    private String userEmail;

    @ApiModelProperty(value = "角色",required = true)
    @NotEmpty(message = "角色不能为空",groups = {Create.class, Update.class})
    private List<Integer> roles;
}
