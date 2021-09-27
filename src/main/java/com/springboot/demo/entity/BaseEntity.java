package com.springboot.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class BaseEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(hidden = true)
    private String createUser;

    @ApiModelProperty(hidden = true)
    private LocalDateTime createTime;

    @ApiModelProperty(hidden = true)
    private LocalDateTime updateTime;

    @ApiModelProperty(hidden = true)
    private String updateUser;

}
