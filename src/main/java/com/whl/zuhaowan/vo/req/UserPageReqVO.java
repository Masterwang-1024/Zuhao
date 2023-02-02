package com.whl.zuhaowan.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserPageReqVO {
    @ApiModelProperty(value = "第几页")
    private int pageNum=1;

    @ApiModelProperty(value = "当前页的数量")
    private int pageSize;

    private String id;

    private String username;

    private String salt;

    private String password;

    private String phone;

    private String realName;

    private String nickName;

    private String email;

    private Byte status;

    private Byte sex;

    private Byte deleted;

    private String createId;

    private String updateId;

    private Byte createWhere;

    private Date createTime;

    private Date updateTime;

    private String companyName;

}
