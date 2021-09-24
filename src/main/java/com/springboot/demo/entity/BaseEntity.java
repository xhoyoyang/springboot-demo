package com.springboot.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class BaseEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String createUser;

    private Date createTime;

    private Date updateTime;

    private String updateUser;

}
