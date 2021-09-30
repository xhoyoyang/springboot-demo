package com.springboot.demo.entity;

import com.springboot.demo.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Role extends BaseEntity {

    private String roleName;

    private String roleDesc;
}
