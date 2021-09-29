package com.springboot.demo.entity;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoleMenu extends BaseEntity{

    private Integer roleId;

    private Integer menuId;
}
