package com.whl.zuhaowan.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: UserAddReqVO
 * TODO:类文件简单描述
 * @Author: liuxl
 * @UpdateUser: liuxl
 * @Version: 0.0.1
 */
@Data
public class UserAddReqVO {

    @ApiModelProperty(value = "账号")
    @NotBlank(message = "账号不能为空")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "创建来源(1.web注册 2.后台管理员手动添加 )")
    private Integer createWhere;

    @ApiModelProperty(value = "账户状态(1.正常 2.锁定 )")
    private Integer status;

    @ApiModelProperty(value="真实姓名")
    private String realName;

    @ApiModelProperty(value="公司名称")
    private String companyName;
}
