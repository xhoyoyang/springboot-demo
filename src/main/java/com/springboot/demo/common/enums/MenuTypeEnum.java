package com.springboot.demo.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum MenuTypeEnum {

    page(1, "页面，路由"),
    api(2, "API接口"),
    button(3, "按钮");

    @EnumValue
    private Integer code;
    private String name;

    MenuTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static MenuTypeEnum getByCode(String val) {
        MenuTypeEnum[] vals = values();
        for (MenuTypeEnum v : vals) {
            if (v.getCode().equals(val)) {
                return v;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
