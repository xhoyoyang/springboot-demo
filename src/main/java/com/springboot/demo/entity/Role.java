package com.springboot.demo.entity;

import com.springboot.demo.common.entity.BaseEntity;
import lombok.Data;

@Data
public class Role extends BaseEntity {

    private String roleName;

    private String roleDesc;
}
