package com.whl.zuhaowan.service;


import com.whl.zuhaowan.vo.req.UserOwnRoleReqVO;

import java.util.List;

/**
 * @ClassName: UserRoleService
 * TODO:类文件简单描述
 * @Author: liuxl
 * @UpdateUser: liuxl
 * @Version: 0.0.1
 */
public interface UserRoleService {

    List<String> getRoleIdsByUserId(String userId);
    void addUserRoleInfo(UserOwnRoleReqVO vo);
    List<String> getUserIdsByRoleIds(List<String> roleIds);
    List<String> getUserIdsBtRoleId(String roleId);
    int removeUserRoleId(String roleId);
}
