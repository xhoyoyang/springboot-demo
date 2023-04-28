package com.springboot.demo.controller.request;

import com.springboot.demo.validate.group.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RoleRequest {

    @ApiModelProperty("id,修改时不能为空")
    @NotNull(message = "id cant not be null", groups = {Update.class})
    private Integer id;

    @ApiModelProperty("角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    @ApiModelProperty("角色描述")
    private String roleDesc;

    @ApiModelProperty("权限")
    @NotEmpty(message = "权限不能为空")
    private List<Integer> menus;

}
