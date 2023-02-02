package com.whl.zuhaowan.mapper;

import com.whl.zuhaowan.entity.SysUser;
import com.whl.zuhaowan.vo.req.UserPageReqVO;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    List<SysUser> selectAll(UserPageReqVO vo);

    SysUser selectByuserName(String username);
}