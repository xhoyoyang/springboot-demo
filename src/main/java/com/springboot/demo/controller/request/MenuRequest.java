package com.springboot.demo.controller.request;

import com.springboot.demo.common.enums.MenuTypeEnum;
import com.springboot.demo.validate.annotation.EnumValidAnnotation;
import com.springboot.demo.validate.group.Update;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MenuRequest {

    @NotNull(message = "id can not be null",groups = Update.class)
    private Integer id;

    @NotBlank(message = "菜单名不能为空")
    private String menuName;

    @NotNull(message = "菜单类型不能为空")
    @EnumValidAnnotation(target = MenuTypeEnum.class)
    private MenuTypeEnum menuType;

    @NotBlank(message = "url不能为空")
    private String menuUrl;
}
