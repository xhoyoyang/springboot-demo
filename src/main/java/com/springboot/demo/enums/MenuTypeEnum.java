package com.springboot.demo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum MenuTypeEnum {

    page(1,"页面，路由"),
    api(2,"API接口"),
    button(3,"按钮")
    ;

    MenuTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @EnumValue
    private Integer code;

    private String name;

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
