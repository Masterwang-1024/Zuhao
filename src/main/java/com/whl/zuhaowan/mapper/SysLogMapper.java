package com.whl.zuhaowan.mapper;

import com.whl.zuhaowan.entity.SysLog;
import com.whl.zuhaowan.vo.req.LogPageReqVO;

import java.util.List;

public interface SysLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);

    List<SysLog> selectAll(LogPageReqVO vo);
}