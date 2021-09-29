package com.springboot.demo.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRole extends BaseEntity {

    private Integer userId;

    private Integer roleId;
}
