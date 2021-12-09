package com.springboot.demo.controller.request;


import com.springboot.demo.common.entity.BaseParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserQueryRequest extends BaseParams {

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("手机号")
    private String userMobile;


}
