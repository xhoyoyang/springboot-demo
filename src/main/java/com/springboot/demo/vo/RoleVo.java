package com.springboot.demo.vo;

import com.springboot.demo.vo.common.BaseVo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleVo extends BaseVo {

    private String roleName;

    private String roleDesc;

}
