package com.whl.zuhaowan.service;

import com.whl.zuhaowan.entity.SysPermission;
import com.whl.zuhaowan.vo.req.PermissionAddReqVO;
import com.whl.zuhaowan.vo.req.PermissionUpdateReqVO;
import com.whl.zuhaowan.vo.resp.PermissionRespNodeVO;

import java.util.List;

public interface PermissionService {
    List<SysPermission> selectAll();
    List<PermissionRespNodeVO> selectAllMenuByTree();
    SysPermission addPermission(PermissionAddReqVO vo);
    List<PermissionRespNodeVO> permissionTreeList(String userId);
    List<PermissionRespNodeVO> selectAllTree();
    void updatePermission(PermissionUpdateReqVO vo);
    void deletedPermission(String permissionId);
    List<String> getPermissionByUserId(String userId);
    List<SysPermission> getPermissions(String userId);
}
