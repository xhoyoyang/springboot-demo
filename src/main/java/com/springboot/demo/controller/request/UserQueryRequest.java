package com.springboot.demo.controller.request;


import com.springboot.demo.common.entity.BaseParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserQueryRequest extends BaseParams {

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("手机号")
    private String userMobile;


}
