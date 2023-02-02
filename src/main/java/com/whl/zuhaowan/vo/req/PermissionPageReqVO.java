package com.whl.zuhaowan.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PermissionPageReqVO {

    @ApiModelProperty(value = "第几页")
    private int pageNum=1;
    @ApiModelProperty(value = "当前页的数量")
    private int pageSize;

    private String id;

    private String code;

    private String name;

    private String description;

    private Date createTime;

    private Date updateTime;

    private String del;
}
