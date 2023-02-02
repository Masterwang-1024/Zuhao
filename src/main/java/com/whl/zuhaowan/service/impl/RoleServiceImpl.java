package com.whl.zuhaowan.service.impl;

import com.github.pagehelper.PageHelper;
import com.whl.zuhaowan.mapper.SysRoleMapper;
import com.whl.zuhaowan.utils.PageUtil;
import com.whl.zuhaowan.vo.req.AddRoleReqVO;
import com.whl.zuhaowan.vo.req.RoleUpdateReqVO;
import com.whl.zuhaowan.vo.resp.PageVO;
import com.whl.zuhaowan.entity.SysRole;
import com.whl.zuhaowan.exception.BusinessException;
import com.whl.zuhaowan.exception.code.BaseResponseCode;
import com.whl.zuhaowan.service.RoleService;
import com.whl.zuhaowan.vo.req.RolePageReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public PageVO<SysRole> pageInfo(RolePageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<SysRole> sysRoles =sysRoleMapper.selectAll(vo);
        return PageUtil.getPageVO(sysRoles);
    }

    @Override
    public SysRole addRole(AddRoleReqVO vo) {
        SysRole sysRole=new SysRole();
        BeanUtils.copyProperties(vo,sysRole);
        sysRole.setId(UUID.randomUUID().toString());
        sysRole.setCreateTime(new Date());
        int i = sysRoleMapper.insertSelective(sysRole);
        if(i!=1){
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        return sysRole;
    }

    @Override
    public List<SysRole> selectAll() {
        return sysRoleMapper.selectAll(new RolePageReqVO());
    }

    @Override
    public SysRole detailInfo(String id) {
        //通过id获取角色信息
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(id);
        if(sysRole==null){
            log.error("传入 的 id:{}不合法",id);
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        return sysRole;
    }

    @Override
    public void updateRole(RoleUpdateReqVO vo) {
        //保存角色基本信息
        SysRole sysRole=sysRoleMapper.selectByPrimaryKey(vo.getId());
        if (null==sysRole){
            log.error("传入 的 id:{}不合法",vo.getId());
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        BeanUtils.copyProperties(vo,sysRole);
        sysRole.setUpdateTime(new Date());
        int count=sysRoleMapper.updateByPrimaryKeySelective(sysRole);
        if(count!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }

    @Override
    public void deletedRole(String roleId) {
        //就更新删除的角色数据
        SysRole sysRole=new SysRole();
        sysRole.setId(roleId);
        sysRole.setUpdateTime(new Date());
        int i = sysRoleMapper.deleteByPrimaryKey(roleId);
        if(i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }




}
