package com.whl.zuhaowan.vo.req;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateUserReqVO {
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
