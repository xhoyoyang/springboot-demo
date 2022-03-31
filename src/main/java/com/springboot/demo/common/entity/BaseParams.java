package com.springboot.demo.common.entity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class BaseParams {

    @ApiModelProperty("当前页")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页大小", example = "10")
    private Integer pageSize = 10;

    @ApiModelProperty(hidden = true)
    @ToString.Exclude
    private Page page;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    public Page getPage() {
        return page == null ? new Page(pageNum, pageSize) : page;
    }
}
