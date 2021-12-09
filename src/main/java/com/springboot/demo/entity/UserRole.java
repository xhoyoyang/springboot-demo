package com.springboot.demo.entity;

import com.springboot.demo.common.entity.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserRole extends BaseEntity {

    private Integer userId;

    private Integer roleId;
}
