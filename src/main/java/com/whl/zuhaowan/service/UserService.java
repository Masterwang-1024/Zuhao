package com.whl.zuhaowan.service;



import com.whl.zuhaowan.entity.SysRolePermission;
import com.whl.zuhaowan.entity.SysUser;
import com.whl.zuhaowan.entity.SysUserRole;
import com.whl.zuhaowan.vo.req.*;
import com.whl.zuhaowan.vo.resp.LoginRespVO;
import com.whl.zuhaowan.vo.resp.PageVO;


public interface UserService {

    SysUser addUser(AddUserReqVO vo);

    void deleteUserById(String id);

    SysUser updateUser(UpdateUserReqVO vo);

    PageVO<SysUser> pageInfo(UserPageReqVO vo);

    SysUser detailUser(String id);

    LoginRespVO login(LoginReqVO vo);

    SysUserRole addUserRole(AddUserRoleReqVO vo);

    SysRolePermission addRolePermission(AddRolePermission vo);
}
