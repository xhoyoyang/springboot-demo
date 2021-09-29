package com.springboot.demo.controller.request;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.demo.validate.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
public class BaseParams {

    @ApiModelProperty("当前页")
    private Integer pageNum = 1;

    @ApiModelProperty("每页大小")
    private Integer pageSize = 10;

    @ApiModelProperty(hidden = true)
    private Page page = new Page(pageNum,pageSize);

    @NotNull(message = "id cant be null",groups = {Update.class})
    private Integer id;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

}