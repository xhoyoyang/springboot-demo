package com.springboot.demo.vo;

import com.springboot.demo.common.entity.BaseVo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleVo extends BaseVo {

    private String roleName;

    private String roleDesc;

}
