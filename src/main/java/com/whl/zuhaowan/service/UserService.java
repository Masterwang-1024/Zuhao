package com.whl.zuhaowan.service;



import com.whl.zuhaowan.entity.SysRolePermission;
import com.whl.zuhaowan.entity.SysUser;
import com.whl.zuhaowan.entity.SysUserRole;
import com.whl.zuhaowan.vo.req.*;
import com.whl.zuhaowan.vo.resp.LoginRespVO;
import com.whl.zuhaowan.vo.resp.PageVO;
import com.whl.zuhaowan.vo.resp.UserOwnRoleRespVO;

import java.util.List;


public interface UserService {


    LoginRespVO login(LoginReqVO vo);


    PageVO<SysUser> pageInfo(UserPageReqVO vo);

    void addUser(UserAddReqVO vo);

    UserOwnRoleRespVO getUserOwnRole(String userId);

    void setUserOwnRole(UserOwnRoleReqVO vo);

    String refreshToken(String refreshToken);

    void updateUserInfo(UserUpdateReqVO vo, String operationId);

    void deletedUsers(List<String> list, String operationId);

    List<SysUser> selectUserInfoByDeptIds(List<String> deptIds);

    SysUser detailInfo(String userId);

    //个人用户编辑信息接口
    void userUpdateDetailInfo(UserUpdateDetailInfoReqVO vo,String userId);

    void userUpdatePwd(UserUpdatePwdReqVO vo,String accessToken,String refreshToken);

    void logout(String accessToken,String refreshToken);
}
