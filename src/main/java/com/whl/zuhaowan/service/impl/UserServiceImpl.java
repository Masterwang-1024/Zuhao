package com.whl.zuhaowan.service.impl;


import com.github.pagehelper.PageHelper;
import com.whl.zuhaowan.contants.Constant;
import com.whl.zuhaowan.entity.SysUser;

import com.whl.zuhaowan.mapper.SysRoleMapper;
import com.whl.zuhaowan.mapper.SysUserMapper;
import com.whl.zuhaowan.service.PermissionService;
import com.whl.zuhaowan.service.RoleService;
import com.whl.zuhaowan.service.UserRoleService;
import com.whl.zuhaowan.service.UserService;
import com.whl.zuhaowan.entity.SysRolePermission;
import com.whl.zuhaowan.entity.SysUserRole;
import com.whl.zuhaowan.exception.BusinessException;
import com.whl.zuhaowan.exception.code.BaseResponseCode;
import com.whl.zuhaowan.mapper.SysRolePermissionMapper;
import com.whl.zuhaowan.mapper.SysUserRoleMapper;

import com.whl.zuhaowan.utils.*;
import com.whl.zuhaowan.vo.req.*;
import com.whl.zuhaowan.vo.resp.LoginRespVO;
import com.whl.zuhaowan.vo.resp.PageVO;
import com.whl.zuhaowan.vo.resp.UserOwnRoleRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Value("${user.default.role}")
    private String userDefaultRole;

    @Resource
    private SysUserMapper sysUserMapper;
//    @Autowired
//    private PaasPlatformService paasPlatformService;
//    @Autowired
//    private RedisService redisService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private TokenSettings tokenSettings;
    @Autowired
    private PermissionService permissionService;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public LoginRespVO login(LoginReqVO vo) {
        //通过用户名查询用户信息
        //如果查询存在用户
        //就比较它密码是否一样
        SysUser  userInfoByName = sysUserMapper.getUserInfoByName(vo.getUsername());
        if(userInfoByName==null){
            throw new BusinessException(BaseResponseCode.ACCOUNT_ERROR);
        }
        if(userInfoByName.getStatus()==2){
            throw new BusinessException(BaseResponseCode.ACCOUNT_LOCK_TIP);
        }
        //RSA解密
        String decryptPassword = null;
        try {
            decryptPassword = RSAEncrypt.decrypt(vo.getPassword(),Constant.RSA_PRIVATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!PasswordUtils.matches(userInfoByName.getSalt(),decryptPassword,userInfoByName.getPassword())){
            throw new BusinessException(BaseResponseCode.ACCOUNT_PASSWORD_ERROR);
        }
        LoginRespVO loginRespVO=new LoginRespVO();
        loginRespVO.setPhone(userInfoByName.getPhone());
        loginRespVO.setUsername(userInfoByName.getUsername());
        loginRespVO.setId(userInfoByName.getId());

        Map<String, Object> claims=new HashMap<>();
        UserOwnRoleRespVO userOwnRole = getUserOwnRole(userInfoByName.getId());
        loginRespVO.setRole(userOwnRole.getOwnRoles());

//        sysRoleMapper.selectByPrimaryKey()
//        claims.put(Constant.ROLES_INFOS_KEY,getRoleByUserId(userInfoByName.getId()));
//        claims.put(Constant.PERMISSIONS_INFOS_KEY,getPermissionByUserId(userInfoByName.getId()));
//        redisService.set(Constant.PERMISSIONS_INFOS_KEY+userInfoByName.getId(),getPermissionByUserId(userInfoByName.getId()));
//        redisService.set(Constant.ROLES_INFOS_KEY+userInfoByName.getId(),getRoleByUserId(userInfoByName.getId()));
        claims.put(Constant.JWT_USER_NAME,userInfoByName.getUsername());
        String accessToken= JwtTokenUtil.getAccessToken(userInfoByName.getId(),claims);
        String refreshToken;
        if(vo.getType().equals("1")){
            refreshToken=JwtTokenUtil.getRefreshToken(userInfoByName.getId(),claims);
        }else {
            refreshToken=JwtTokenUtil.getRefreshAppToken(userInfoByName.getId(),claims);
        }
        loginRespVO.setAccessToken(accessToken);
        loginRespVO.setRefreshToken(refreshToken);
//        paasPlatformService.paasLogin(loginRespVO.getId());
        return loginRespVO;
    }

    @Override
    public PageVO<SysUser> pageInfo(UserPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<SysUser> list=sysUserMapper.selectAll(vo);

        return PageUtil.getPageVO(list);
    }

    @Override
    public void addUser(UserAddReqVO vo) {
        SysUser sysUser=new SysUser();
        //RSA解密
        String decryptPassword = null;
        try {
            decryptPassword = RSAEncrypt.decrypt(vo.getPassword(), Constant.RSA_PRIVATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        vo.setPassword(decryptPassword);
        sysUser.setUsername(vo.getUsername());
        int count = sysUserMapper.getCount(sysUser);
        if(count>0){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR.getCode(),"账号已被占用");
        }
        BeanUtils.copyProperties(vo,sysUser);
        sysUser.setId(UUID.randomUUID().toString());
        sysUser.setCreateTime(new Date());
        String salt= PasswordUtils.getSalt();
        String ecdPwd=PasswordUtils.encode(vo.getPassword(),salt);
        sysUser.setSalt(salt);
        sysUser.setPassword(ecdPwd);
        int i = sysUserMapper.insertSelective(sysUser);
        if(i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
        /*新增用户添加默认role*/
        String userId = sysUser.getId();
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setId(UUID.randomUUID().toString());
        sysUserRole.setUserId(userId);
        sysUserRole.setCreateTime(new Date());
        sysUserRole.setRoleId(userDefaultRole);
        int insert = sysUserRoleMapper.insert(sysUserRole);
        if(insert!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR.getCode(),"初始化用户权限出错");
        }
    }

    @Override
    public UserOwnRoleRespVO getUserOwnRole(String userId) {
        UserOwnRoleRespVO respVO=new UserOwnRoleRespVO();
        List<String> roleIds = userRoleService.getRoleIdsByUserId(userId);
        List<String> roleNames = sysRoleMapper.selectNamesByIds(roleIds);
        respVO.setOwnRoles(roleNames);
        respVO.setAllRole(roleService.selectAll());
        return respVO;
    }

    @Override
    public void setUserOwnRole(UserOwnRoleReqVO vo) {

        userRoleService.addUserRoleInfo(vo);
//        /**
//         * 标记用户 要主动去刷新
//         */
//        redisService.set(Constant.JWT_REFRESH_KEY+vo.getUserId(),vo.getUserId(),tokenSettings.getAccessTokenExpireTime().toMillis(),TimeUnit.MILLISECONDS);
//        /**
//         * 清楚用户授权数据缓存
//         */
//        redisService.delete(Constant.IDENTIFY_CACHE_KEY+vo.getUserId());

    }

    @Override
    public String refreshToken(String refreshToken) {
        return null;
    }

    @Override
    public void updateUserInfo(UserUpdateReqVO vo, String operationId) {

    }

    @Override
    public void deletedUsers(List<String> list, String operationId) {
        SysUser sysUser=new SysUser();
        sysUser.setUpdateId(operationId);
        sysUser.setUpdateTime(new Date());
        int i = sysUserMapper.deletedUsers(sysUser, list);
        if(i==0){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
//        for (String userId:
//                list) {
//            redisService.set(Constant.DELETED_USER_KEY+userId,userId,tokenSettings.getRefreshTokenExpireAppTime().toMillis(), TimeUnit.MILLISECONDS);
//            /**
//             * 清楚用户授权数据缓存
//             */
//            redisService.delete(Constant.IDENTIFY_CACHE_KEY+userId);
//        }
    }

    @Override
    public List<SysUser> selectUserInfoByDeptIds(List<String> deptIds) {
        return null;
    }

    @Override
    public SysUser detailInfo(String userId) {
        return null;
    }

    @Override
    public void userUpdateDetailInfo(UserUpdateDetailInfoReqVO vo, String userId) {

    }

    @Override
    public void userUpdatePwd(UserUpdatePwdReqVO vo, String accessToken, String refreshToken) {

    }

    @Override
    public void logout(String accessToken, String refreshToken) {

    }


}
