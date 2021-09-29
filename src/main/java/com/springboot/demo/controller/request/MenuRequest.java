package com.springboot.demo.controller.request;

import com.springboot.demo.enums.MenuTypeEnum;
import com.springboot.demo.validate.Create;
import com.springboot.demo.validate.Update;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class MenuRequest {

    @NotNull(message = "id can not be null",groups = Update.class)
    private Integer id;

    @NotBlank(message = "菜单名不能为空")
    private String menuName;

    @NotNull(message = "菜单类型不能为空")
    private MenuTypeEnum menuType;

    @NotNull(message = "url不能为空")
    private String menuUrl;
}
