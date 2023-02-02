package com.whl.zuhaowan.service.impl;

import com.github.pagehelper.PageHelper;

import com.whl.zuhaowan.entity.SysLog;
import com.whl.zuhaowan.exception.BusinessException;
import com.whl.zuhaowan.exception.code.BaseResponseCode;
import com.whl.zuhaowan.mapper.SysLogMapper;
import com.whl.zuhaowan.service.LogService;
import com.whl.zuhaowan.utils.PageUtil;
import com.whl.zuhaowan.vo.req.LogPageReqVO;
import com.whl.zuhaowan.vo.resp.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LogServiceImpl implements LogService {
    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public PageVO<SysLog> pageInfo(LogPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<SysLog> sysLogs = sysLogMapper.selectAll(vo);
        return PageUtil.getPageVO(sysLogs);
    }

    @Override
    public void deleteLogById(String id) {
        int i = sysLogMapper.deleteByPrimaryKey(id);
        if (i!=1){
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
    }
}
