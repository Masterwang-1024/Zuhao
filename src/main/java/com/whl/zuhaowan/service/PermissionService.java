package com.whl.zuhaowan.service;

import com.whl.zuhaowan.entity.SysPermission;
import com.whl.zuhaowan.vo.req.AddPermissionReqVO;
import com.whl.zuhaowan.vo.req.PermissionPageReqVO;
import com.whl.zuhaowan.vo.req.UpdatePermissionReqVO;
import com.whl.zuhaowan.vo.resp.PageVO;

public interface PermissionService {
    SysPermission addUser(AddPermissionReqVO vo);

    void deleteUserById(String id);

    void updateUser(UpdatePermissionReqVO vo);

    PageVO<SysPermission> pageInfo(PermissionPageReqVO vo);
}
