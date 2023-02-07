package com.whl.zuhaowan.controller;


import com.whl.zuhaowan.aop.annotation.MyLog;
import com.whl.zuhaowan.contants.Constant;
import com.whl.zuhaowan.entity.SysUser;
import com.whl.zuhaowan.service.UserService;
import com.whl.zuhaowan.utils.DataResult;
import com.whl.zuhaowan.utils.JwtTokenUtil;
import com.whl.zuhaowan.vo.req.*;
import com.whl.zuhaowan.vo.resp.LoginRespVO;
import com.whl.zuhaowan.vo.resp.PageVO;
import com.whl.zuhaowan.vo.resp.UserOwnRoleRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(tags = "用户管理-用户信息",description = "用户相关接口")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    @ApiOperation(value = "用户登录接口")
    public DataResult<LoginRespVO> login(@RequestBody @Valid LoginReqVO vo){
        DataResult result=DataResult.success();
        result.setData(userService.login(vo));
        return result;
    }


    @PostMapping("/users")
    @ApiOperation(value = "分页查询用户接口")
    @MyLog(title = "组织管理-用户管理",action = "分页查询用户接口")
    public DataResult<PageVO<SysUser>> pageInfo(@RequestBody UserPageReqVO vo){
        DataResult result=DataResult.success();
        result.setData(userService.pageInfo(vo));
        return result;
    }

    @PostMapping("/web/user")
    @ApiOperation(value = "自注册新增用户接口")
    @MyLog(title = "组织管理-用户管理",action = "web自注册新增用户接口")
    public DataResult webAddUser(@RequestBody @Valid UserAddReqVO vo){
        DataResult result=DataResult.success();
        userService.addUser(vo);
        return result;
    }

    @PostMapping("/user")
    @ApiOperation(value = "后台管理员新增用户接口")
    @MyLog(title = "组织管理-用户管理",action = "后台管理员新增用户接口")
    public DataResult addUser(@RequestBody @Valid UserAddReqVO vo){
        DataResult result=DataResult.success();
        userService.addUser(vo);
        return result;
    }

    @GetMapping("/user/roles/{userId}")
    @ApiOperation(value = "查询用户拥有的角色数据接口")
    @MyLog(title = "组织管理-用户管理",action = "查询用户拥有的角色数据接口")
    public DataResult<UserOwnRoleRespVO> getUserOwnRole(@PathVariable("userId") String userId){
        DataResult result=DataResult.success();
        result.setData(userService.getUserOwnRole(userId));
        return result;
    }

    @PutMapping("/user/roles")
    @ApiOperation(value = "保存用户拥有的角色信息接口")
    @MyLog(title = "组织管理-用户管理",action = "保存用户拥有的角色信息接口")
    public DataResult saveUserOwnRole(@RequestBody @Valid UserOwnRoleReqVO vo){
        DataResult result=DataResult.success();
        userService.setUserOwnRole(vo);
        return result;
    }


    @GetMapping("/user/token")
    @ApiOperation(value = "jwt token 刷新接口")
    @MyLog(title = "组织管理-用户管理",action = "jwt token 刷新接口")
    public DataResult<String> refreshToken(HttpServletRequest request){
        String refreshToken=request.getHeader(Constant.REFRESH_TOKEN);
        String newAccessToken = userService.refreshToken(refreshToken);
        DataResult result=DataResult.success();
        result.setData(newAccessToken);
        return result;
    }

    @PutMapping("/user")
    @ApiOperation(value ="列表修改用户信息接口")
    @MyLog(title = "组织管理-用户管理",action = "列表修改用户信息接口")
    public DataResult updateUserInfo(@RequestBody @Valid UserUpdateReqVO vo, HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        String userId= JwtTokenUtil.getUserId(accessToken);
        DataResult result=DataResult.success();
        userService.updateUserInfo(vo,userId);
        return result;
    }

    @DeleteMapping("/user")
    @ApiOperation(value = "批量/删除用户接口")
    @MyLog(title = "组织管理-用户管理",action = "批量/删除用户接口")
    public DataResult deletedUsers(@RequestBody @ApiParam(value = "用户id集合") List<String> list, HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        String operationId=JwtTokenUtil.getUserId(accessToken);
        userService.deletedUsers(list,operationId);
        DataResult result=DataResult.success();
        return result;
    }

    @GetMapping("/user/info")
    @ApiOperation(value = "用户信息详情接口")
    @MyLog(title = "组织管理-用户管理",action = "用户信息详情接口")
    public DataResult<SysUser> detailInfo(HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        String id=JwtTokenUtil.getUserId(accessToken);
        DataResult result=DataResult.success();
        result.setData(userService.detailInfo(id));
        return result;
    }

    @PutMapping("/user/info")
    @ApiOperation(value = "保存个人信息接口")
    @MyLog(title = "组织管理-用户管理",action = "保存个人信息接口")
    public DataResult saveUserInfo(@RequestBody UserUpdateDetailInfoReqVO vo,HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        String id=JwtTokenUtil.getUserId(accessToken);
        userService.userUpdateDetailInfo(vo,id);
        DataResult result=DataResult.success();
        return result;
    }

    @PutMapping("/user/pwd")
    @ApiOperation(value = "修改个人密码接口")
    public DataResult updatePwd(@RequestBody @Valid UserUpdatePwdReqVO vo,HttpServletRequest request){
        String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
        String refresgToken=request.getHeader(Constant.REFRESH_TOKEN);
        userService.userUpdatePwd(vo,accessToken,refresgToken);
        DataResult result=DataResult.success();
        return result;
    }

    @GetMapping("/user/logout")
    @ApiOperation(value = "用户推出登录")
    public DataResult logout(HttpServletRequest request){
        try {
            String accessToken=request.getHeader(Constant.ACCESS_TOKEN);
            String refreshToken=request.getHeader(Constant.REFRESH_TOKEN);
            userService.logout(accessToken,refreshToken);
        } catch (Exception e) {
//            .error("logout:{}",e);
        }
        return DataResult.success();
    }

}
