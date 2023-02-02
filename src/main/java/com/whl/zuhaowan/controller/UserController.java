package com.whl.zuhaowan.controller;


import com.whl.zuhaowan.aop.annotation.MyLog;
import com.whl.zuhaowan.contants.Constant;
import com.whl.zuhaowan.entity.SysUser;
import com.whl.zuhaowan.service.UserService;
import com.whl.zuhaowan.utils.DataResult;
import com.whl.zuhaowan.vo.req.*;
import com.whl.zuhaowan.vo.resp.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Api(tags = "用户管理-用户信息",description = "用户相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addRolePermission")
    @ApiOperation(value="给角色添加权限")
    @MyLog(title = "用户管理-权限信息",action = "新增角色权限")
    public DataResult addRolePermission(@RequestBody @Valid AddRolePermission vo, HttpServletRequest request){
        DataResult result = DataResult.success();
        result.setData(userService.addRolePermission(vo));
        return result;
    }


    @PostMapping("/addUserRole")
    @ApiOperation(value="给用户添加角色")
    @MyLog(title = "用户管理-权限信息",action = "新增用户角色")
    public DataResult addUserRole(@RequestBody @Valid AddUserRoleReqVO vo, HttpServletRequest request){
        DataResult result = DataResult.success();
        result.setData(userService.addUserRole(vo));
        return result;
    }

    @PostMapping("/addUser")
    @ApiOperation(value="用户注册接口")
    @MyLog(title = "用户管理-用户信息",action = "用户注册接口")
    public DataResult addDatasetTag(@RequestBody @Valid AddUserReqVO vo, HttpServletRequest request){
        DataResult result = DataResult.success();
        result.setData(userService.addUser(vo));
        return result;
    }

    @DeleteMapping("/deleteUserById/{id}")
    @ApiOperation(value="注销用户")
    @MyLog(title = "用户管理-用户信息",action = "注销用户")
    public DataResult deleteUserById(@PathVariable("id") String id,HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        DataResult result = DataResult.success();
        userService.deleteUserById(id);
        return result;
    }

    @PutMapping("/UpdateUser")
    @ApiOperation(value="修改用户信息")
    @MyLog(title = "用户管理-用户信息",action = "修改用户信息")
    public DataResult updateUser(@RequestBody UpdateUserReqVO vo, HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        DataResult result = DataResult.success();
        userService.updateUser(vo);
        return result;
    }

    @PostMapping("/listUser")
    @ApiOperation(value="用户列表接口")
    @MyLog(title = "用户管理-用户信息",action = "用户列表接口")
    public DataResult<PageVO<SysUser>> pageInfo(@RequestBody UserPageReqVO vo, HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        DataResult result = DataResult.success(userService.pageInfo(vo));
        return result;
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value="获取用户信息接口")
    @MyLog(title = "用户管理-用户信息",action = "获取用户信息接口")
    public DataResult detailUser(@PathVariable("id") String id){
        DataResult result = DataResult.success();
        result.setData(userService.detailUser(id));
        return result;
    }

    @PostMapping("/user/login")
    @ApiOperation(value = "用户登录接口")
    @MyLog(title = "用户管理-用户信息",action = "用户登录接口")
    public DataResult login(@RequestBody @Valid LoginReqVO vo, HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        DataResult result=DataResult.success();
        result.setData(userService.login(vo));
        return result;
    }

}
