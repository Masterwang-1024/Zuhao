package com.whl.zuhaowan.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class LogPageReqVO {
    @ApiModelProperty(value = "第几页")
    private int pageNum=1;

    @ApiModelProperty(value = "当前页的数量")
    private int pageSize;

    private String id;

    private String userId;

    private String username;

    private String operation;

    private Integer time;

    private String method;

    private String params;

    private String ip;

    private Date createTime;
}
