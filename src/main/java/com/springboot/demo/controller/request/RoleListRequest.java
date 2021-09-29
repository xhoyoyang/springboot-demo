package com.springboot.demo.controller.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleListRequest extends BaseParams{

    @ApiModelProperty("角色名称")
    private String roleName;
}
