package com.springboot.demo.common.entity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class BaseParams {

    @ApiModelProperty("当前页")
    private Integer pageNum = 1;

    @ApiModelProperty("每页大小")
    private Integer pageSize = 10;

    @ApiModelProperty(hidden = true)
    private Page page;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    public Page getPage() {
        return page == null ? new Page(pageNum,pageSize) : page;
    }
}
