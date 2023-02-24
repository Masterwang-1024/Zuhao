package com.whl.zuhaowan.controller;

import com.whl.zuhaowan.aop.annotation.MyLog;
import com.whl.zuhaowan.entity.SysPermission;
import com.whl.zuhaowan.service.PermissionService;
import com.whl.zuhaowan.utils.DataResult;
import com.whl.zuhaowan.vo.req.PermissionAddReqVO;
import com.whl.zuhaowan.vo.req.PermissionUpdateReqVO;
import com.whl.zuhaowan.vo.resp.PermissionRespNodeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ClassName: PermissionController
 * TODO:类文件简单描述
 * @Author: liuxl
 * @UpdateUser: liuxl
 * @Version: 0.0.1
 */
@RestController
@RequestMapping("/api")
@Api(tags = "组织管理-菜单权限管理",description = "菜单权限管理相关接口")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/permissions")
    @ApiOperation(value = "获取所有的菜单权限数据接口")
    @MyLog(title = "组织管理-菜单权限管理",action = "获取所有的菜单权限数据接口")
    public DataResult<List<SysPermission>> getAllPermission(){
        DataResult result= DataResult.success();
        result.setData(permissionService.selectAll());
        return result;
    }

    @GetMapping("/permission/tree")
    @ApiOperation(value = "菜单权限树接口-只递归查询到菜单接口")
    @MyLog(title = "组织管理-菜单权限管理",action = "只递归查询到菜单接口")
    public DataResult<List<PermissionRespNodeVO>> getAllPermissionTreeExBtn(){
        DataResult result=DataResult.success();
        result.setData(permissionService.selectAllMenuByTree());
        return result;
    }

    @PostMapping("/permission")
    @ApiOperation(value = "新增菜单权限接口")
    @MyLog(title = "组织管理-菜单权限管理",action = "新增菜单权限接口")
    public DataResult<SysPermission> addPermission(@RequestBody @Valid PermissionAddReqVO vo){
        DataResult result=DataResult.success();
        result.setData(permissionService.addPermission(vo));
        return result;
    }

    @GetMapping("/permission/tree/all")
    @ApiOperation(value = "菜单权限树接口-只递归查询所有接口")
    @MyLog(title = "组织管理-菜单权限管理",action = "只递归查询所有接口")
    public DataResult<List<PermissionRespNodeVO>> getAllPermissionTree(){
        DataResult result=DataResult.success();
        result.setData(permissionService.selectAllTree());
        return result;
    }


    @PutMapping("/permission")
    @ApiOperation(value = "编辑菜单权限接口")
    @MyLog(title = "组织管理-菜单权限管理",action = "编辑菜单权限接口")
    public DataResult updatePermission(@RequestBody @Valid PermissionUpdateReqVO vo){
        permissionService.updatePermission(vo);
        DataResult result=DataResult.success();
        return result;
    }

    @DeleteMapping("/permission/{permissionId}")
    @ApiOperation(value = "删除菜单权限接口")
    @MyLog(title = "组织管理-菜单权限管理",action = "删除菜单权限接口")
    public DataResult deletedPermission(@PathVariable("permissionId") String permissionId){
        DataResult result=DataResult.success();
        permissionService.deletedPermission(permissionId);
        return result;
    }
}
