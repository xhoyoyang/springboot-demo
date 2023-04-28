package com.springboot.demo.vo;

import com.springboot.demo.common.enums.MenuTypeEnum;
import lombok.Data;

@Data
public class MenuVo {

    private Integer id;

    private Integer parentId;

    private String menuName;

    private MenuTypeEnum menuType;

    private String menuUrl;


}
