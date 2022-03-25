package com.springboot.demo.controller.request;

import com.springboot.demo.common.entity.BaseParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleQueryRequest extends BaseParams {

    @ApiModelProperty("角色名称")
    private String roleName;
}
