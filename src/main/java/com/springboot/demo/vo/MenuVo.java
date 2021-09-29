package com.springboot.demo.vo;

import com.springboot.demo.enums.MenuTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MenuVo {

    private Integer id;

    private Integer parentId;

    private String menuName;

    private MenuTypeEnum menuType;

    private String menuUrl;


}
