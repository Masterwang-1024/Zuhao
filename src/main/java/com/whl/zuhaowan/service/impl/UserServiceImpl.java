package com.whl.zuhaowan.service.impl;


import com.github.pagehelper.PageHelper;
import com.whl.zuhaowan.entity.SysUser;

import com.whl.zuhaowan.mapper.SysUserMapper;
import com.whl.zuhaowan.service.UserService;
import com.whl.zuhaowan.entity.SysRolePermission;
import com.whl.zuhaowan.entity.SysUserRole;
import com.whl.zuhaowan.exception.BusinessException;
import com.whl.zuhaowan.exception.code.BaseResponseCode;
import com.whl.zuhaowan.mapper.SysRolePermissionMapper;
import com.whl.zuhaowan.mapper.SysUserRoleMapper;

import com.whl.zuhaowan.utils.JwtTokenUtil;
import com.whl.zuhaowan.utils.PageUtil;
import com.whl.zuhaowan.vo.req.*;
import com.whl.zuhaowan.vo.resp.LoginRespVO;
import com.whl.zuhaowan.vo.resp.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper userInfoMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public SysUser addUser(AddUserReqVO vo) {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(vo,user);
        user.setId(UUID.randomUUID().toString());
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        int i = userInfoMapper.insertSelective(user);
        if (i!=1){
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        return user;
    }

    @Override
    public void deleteUserById(String id) {
        int i = userInfoMapper.deleteByPrimaryKey(id);
        if (i!=1){
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
    }

    @Override
    public SysUser updateUser(UpdateUserReqVO vo) {
        SysUser userInfo = new SysUser();
        BeanUtils.copyProperties(vo,userInfo);
        int i = userInfoMapper.updateByPrimaryKeySelective(userInfo);
        if (i!=1){
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        return userInfo;
    }

    @Override
    public PageVO<SysUser> pageInfo(UserPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<SysUser> list = userInfoMapper.selectAll(vo);
        return PageUtil.getPageVO(list);
    }

    @Override
    public SysUser detailUser(String id) {
        SysUser userInfo = userInfoMapper.selectByPrimaryKey(id);
        return userInfo;
    }

    @Override
    public LoginRespVO login(LoginReqVO vo) {
        SysUser userInfo = userInfoMapper.selectByuserName(vo.getUsername());
        LoginRespVO loginRespVO = new LoginRespVO();
        if(userInfo==null){
            throw new BusinessException(BaseResponseCode.ACCOUNT_ERROR);
        }

        if(!userInfo.getPassword().equals(vo.getPassword())){
            throw new BusinessException(BaseResponseCode.ACCOUNT_PASSWORD_ERROR);
        }
        loginRespVO.setId(userInfo.getId());
        List<String> roleIdsByUserId = sysUserRoleMapper.getRoleIdsByUserId(userInfo.getId());
        int max =0;
        for (String s : roleIdsByUserId){
            int i = Integer.parseInt(s);
            if (i>max){
                max =i;
            }
        }
        /**
         *生成token
         *  */
        Map<String, Object> claims=new HashMap<>();
        claims.put("role",max);
        /**将除了密码之外，所有用户信息放入token**/
        for (Field field:userInfo.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if(!(field.getName().equals("password") || field.getName().equals("salt"))){
                    claims.put(field.getName(),field.get(userInfo));
                }
            } catch (IllegalAccessException e) {
                throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
            }
        }
        String accessToken= JwtTokenUtil.getAccessToken(userInfo.getId(), claims);

        loginRespVO.setUsername(vo.getUsername());
        loginRespVO.setRole(roleIdsByUserId);
        loginRespVO.setAccessToken(accessToken);
        return loginRespVO;
    }

    @Override
    public SysUserRole addUserRole(AddUserRoleReqVO vo) {
        SysUserRole sysUserRole = new SysUserRole();
        BeanUtils.copyProperties(vo,sysUserRole);
        sysUserRole.setId(UUID.randomUUID().toString());
        sysUserRole.setCreateTime(new Date());
        int i = sysUserRoleMapper.insertSelective(sysUserRole);
        if (i!=1){
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        return sysUserRole;
    }

    @Override
    public SysRolePermission addRolePermission(AddRolePermission vo) {
        SysRolePermission sysRolePermission = new SysRolePermission();
        BeanUtils.copyProperties(vo,sysRolePermission);
        sysRolePermission.setId(UUID.randomUUID().toString());
        sysRolePermission.setCreateTime(new Date());
        int i = sysRolePermissionMapper.insertSelective(sysRolePermission);
        if (i!=1){
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }

        return sysRolePermission;
    }

}
