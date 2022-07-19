package com.springboot.demo.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum UserTypeEnum {

    /**
     * user type
     */
    admin(1, "管理员"),
    user(2, "普通用户");

    @EnumValue
    private final Integer code;
    private final String name;

    UserTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
