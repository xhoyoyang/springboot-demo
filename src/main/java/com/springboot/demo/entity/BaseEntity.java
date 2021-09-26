package com.springboot.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
@Getter
public class BaseEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String createUser;

    private LocalDateTime createTime;

    private LocalTime updateTime;

    private String updateUser;

}
