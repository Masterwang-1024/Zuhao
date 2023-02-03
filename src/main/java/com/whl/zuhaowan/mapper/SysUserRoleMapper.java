package com.whl.zuhaowan.mapper;

import com.whl.zuhaowan.entity.SysUserRole;

import java.util.List;

public interface SysUserRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);

    List<String> getRoleIdsByUserId(String userId);

    void removeRoleByUserId(String userId);

    int batchInsertUserRole(List<SysUserRole> list);

    List<String> getUserIdsByRoleIds(List<String> roleIds);

    List<String> getUserIdsByRoleId(String roleId);

    int removeUserRoleId(String roleId);
}