package com.whl.zuhaowan.mapper;

import com.whl.zuhaowan.entity.SysPermission;
import com.whl.zuhaowan.vo.req.PermissionPageReqVO;

import java.util.List;

public interface SysPermissionMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

    List<SysPermission> selectAll(PermissionPageReqVO vo);
}