package com.springboot.demo.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(hidden = true)
    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    @ApiModelProperty(hidden = true)
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(hidden = true)
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(hidden = true)
    @TableField(fill = FieldFill.UPDATE)
    private String updateUser;

    @ApiModelProperty(hidden = true)
    private Integer flag;

}
