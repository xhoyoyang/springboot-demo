package com.springboot.demo.entity;

import com.springboot.demo.common.entity.BaseEntity;
import com.springboot.demo.common.enums.MenuTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Menu extends BaseEntity {

    private Integer parentId;

    private String menuName;

    private MenuTypeEnum menuType;

    private String menuUrl;
}
