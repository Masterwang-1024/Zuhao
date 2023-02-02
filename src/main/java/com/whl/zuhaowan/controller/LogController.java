package com.whl.zuhaowan.controller;


import com.whl.zuhaowan.aop.annotation.MyLog;
import com.whl.zuhaowan.contants.Constant;
import com.whl.zuhaowan.entity.SysLog;
import com.whl.zuhaowan.service.LogService;
import com.whl.zuhaowan.utils.DataResult;
import com.whl.zuhaowan.vo.req.LogPageReqVO;
import com.whl.zuhaowan.vo.resp.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
@Api(tags = "日志管理-日志管理",description = "日志相关接口")
public class LogController {
    @Autowired
    private LogService logService;

    @PostMapping("/listLogs")
    @ApiOperation(value="日志列表接口")
    @MyLog(title = "日志管理-日志管理",action = "日志查询接口")
    public DataResult<PageVO<SysLog>> pageInfo(@RequestBody LogPageReqVO vo, HttpServletRequest request){
        DataResult result = DataResult.success(logService.pageInfo(vo));
        return result;
    }

    @DeleteMapping("/deleteLogById/{id}")
    @ApiOperation(value="删除日志")
    @MyLog(title = "日志管理-日志管理",action = "删除日志接口")
    public DataResult deleteNewsById(@PathVariable("id") String id,HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        DataResult result = DataResult.success();
        logService.deleteLogById(id);
        return result;
    }
}
