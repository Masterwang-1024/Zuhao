package com.whl.zuhaowan.mapper;

import com.whl.zuhaowan.entity.SysRole;
import com.whl.zuhaowan.vo.req.RolePageReqVO;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> selectAll(RolePageReqVO vo);

    List<String> selectNamesByIds(List<String> ids);
}