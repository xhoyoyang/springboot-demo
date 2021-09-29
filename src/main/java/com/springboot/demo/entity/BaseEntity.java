package com.springboot.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.springboot.demo.Utils.AuthorizationUtil;
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

    @ApiModelProperty(hidden = true)
    private Integer flag;

    public void buildForCreate(){
        this.createUser = AuthorizationUtil.currentUser().getUserAccount();
        this.createTime = LocalDateTime.now();
        this.updateUser = AuthorizationUtil.currentUser().getUserAccount();
        this.updateTime = LocalDateTime.now();
    }

    public void buildForUpdatee(){
        this.updateUser = AuthorizationUtil.currentUser().getUserAccount();
        this.updateTime = LocalDateTime.now();
    }

}
