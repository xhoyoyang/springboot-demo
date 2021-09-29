package com.springboot.demo.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum FlagEmun {

    t(1,"有效"),
    f(0,"无效")
    ;

    FlagEmun(Integer code, String name) {
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
