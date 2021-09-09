package com.springboot.demo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum UserTypeEnum {

    admin(1,"管理员"),
    user(2,"普通用户")
    ;
    UserTypeEnum(Integer code, String name) {
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
