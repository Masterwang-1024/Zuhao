package com.whl.zuhaowan.controller;

import com.whl.zuhaowan.aop.annotation.MyLog;
import com.whl.zuhaowan.contants.Constant;
import com.whl.zuhaowan.service.PermissionService;
import com.whl.zuhaowan.utils.DataResult;
import com.whl.zuhaowan.vo.req.AddPermissionReqVO;
import com.whl.zuhaowan.vo.req.PermissionPageReqVO;
import com.whl.zuhaowan.vo.req.UpdatePermissionReqVO;
import com.whl.zuhaowan.vo.resp.PageVO;
import com.whl.zuhaowan.entity.SysPermission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Api(tags = "用户管理-权限管理",description = "权限管理")
public class PermissionController {


    @Autowired
    private PermissionService permissionService;




    @PostMapping("/addPermission")
    @ApiOperation(value="新增权限接口")
    @MyLog(title = "用户管理-权限信息",action = "新增权限")
    public DataResult addDatasetTag(@RequestBody @Valid AddPermissionReqVO vo, HttpServletRequest request){
        DataResult result = DataResult.success();
        result.setData(permissionService.addUser(vo));
        return result;
    }

    @DeleteMapping("/deletePermissionById/{id}")
    @ApiOperation(value="删除权限")
    @MyLog(title = "用户管理-权限信息",action = "删除权限")
    public DataResult deletePermissionById(@PathVariable("id") String id, HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        DataResult result = DataResult.success();
        permissionService.deleteUserById(id);
        return result;
    }

    @PutMapping("/UpdatePermission")
    @ApiOperation(value="修改用户信息")
    @MyLog(title = "用户管理-权限信息",action = "修改权限")
    public DataResult updateUser(@RequestBody UpdatePermissionReqVO vo, HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        DataResult result = DataResult.success();
        permissionService.updateUser(vo);
        return result;
    }

    @PostMapping("/listPermission")
    @ApiOperation(value="用户列表接口")
    @MyLog(title = "用户管理-权限信息",action = "新增权限")
    public DataResult<PageVO<SysPermission>> pageInfo(@RequestBody PermissionPageReqVO vo, HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        DataResult result = DataResult.success(permissionService.pageInfo(vo));
        return result;
    }



}
