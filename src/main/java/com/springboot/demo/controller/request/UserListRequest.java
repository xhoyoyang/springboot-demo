package com.springboot.demo.controller.request;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserListRequest extends BaseParams{

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("手机号")
    private String userMobile;


}
