package com.whl.zuhaowan.service;


import com.whl.zuhaowan.entity.SysLog;
import com.whl.zuhaowan.vo.req.LogPageReqVO;
import com.whl.zuhaowan.vo.resp.PageVO;

public interface LogService {
    PageVO<SysLog> pageInfo(LogPageReqVO vo);

    void deleteLogById(String id);
}
