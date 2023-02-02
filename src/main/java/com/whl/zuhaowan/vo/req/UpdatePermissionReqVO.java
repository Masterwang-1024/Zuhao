package com.whl.zuhaowan.vo.req;

import lombok.Data;

import java.util.Date;

@Data
public class UpdatePermissionReqVO {
    private String id;

    private String code;

    private String name;

    private String description;

    private Date createTime;

    private Date updateTime;

    private String del;
}
