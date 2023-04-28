package com.springboot.demo.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseVo {

    private Integer id;

    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("创建时间")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @ApiModelProperty("更新人")
    private String updateUser;

    @ApiModelProperty("状态，1-有效，0-无效")
    private Integer flag;
}
