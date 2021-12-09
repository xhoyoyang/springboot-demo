package com.springboot.demo.vo;

import com.springboot.demo.common.entity.BaseVo;
import lombok.Data;

@Data
public class RoleVo extends BaseVo {

    private String roleName;

    private String roleDesc;

}
