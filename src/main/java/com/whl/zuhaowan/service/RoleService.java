package com.whl.zuhaowan.service;


import com.whl.zuhaowan.vo.req.AddRoleReqVO;
import com.whl.zuhaowan.vo.req.RoleUpdateReqVO;
import com.whl.zuhaowan.vo.resp.PageVO;
import com.whl.zuhaowan.entity.SysRole;
import com.whl.zuhaowan.vo.req.RolePageReqVO;

import java.util.List;

/**
 * @ClassName: RoleService
 * TODO:类文件简单描述
 * @Author: liuxl
 * @UpdateUser: liuxl
 * @Version: 0.0.1
 */
public interface RoleService {
    PageVO<SysRole> pageInfo(RolePageReqVO vo);
    SysRole addRole(AddRoleReqVO vo);
    List<SysRole> selectAll();
    SysRole detailInfo(String id);
    void updateRole(RoleUpdateReqVO vo);
    void deletedRole(String roleId);
    List<String> getNamesByUserId(String userId);
}
