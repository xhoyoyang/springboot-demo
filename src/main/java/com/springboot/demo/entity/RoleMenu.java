package com.springboot.demo.entity;


import com.springboot.demo.common.entity.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RoleMenu extends BaseEntity {

    private Integer roleId;

    private Integer menuId;
}
