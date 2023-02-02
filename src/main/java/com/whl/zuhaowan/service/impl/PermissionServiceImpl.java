package com.whl.zuhaowan.service.impl;


import com.github.pagehelper.PageHelper;
import com.whl.zuhaowan.entity.SysPermission;
import com.whl.zuhaowan.exception.BusinessException;
import com.whl.zuhaowan.exception.code.BaseResponseCode;
import com.whl.zuhaowan.mapper.SysPermissionMapper;
import com.whl.zuhaowan.service.PermissionService;
import com.whl.zuhaowan.utils.PageUtil;
import com.whl.zuhaowan.vo.req.AddPermissionReqVO;
import com.whl.zuhaowan.vo.req.PermissionPageReqVO;
import com.whl.zuhaowan.vo.req.UpdatePermissionReqVO;
import com.whl.zuhaowan.vo.resp.PageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;


    @Override
    public SysPermission addUser(AddPermissionReqVO vo) {
        SysPermission sysRole=new SysPermission();
        BeanUtils.copyProperties(vo,sysRole);
        sysRole.setId(UUID.randomUUID().toString());
        sysRole.setCreateTime(new Date());
        int i = sysPermissionMapper.insertSelective(sysRole);
        if(i!=1){
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        return sysRole;
    }

    @Override
    public void deleteUserById(String id) {
        int i = sysPermissionMapper.deleteByPrimaryKey(id);
        if(i!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }

    @Override
    public void updateUser(UpdatePermissionReqVO vo) {
        //保存角色基本信息
        SysPermission sysPermission = sysPermissionMapper.selectByPrimaryKey(vo.getId());
        if (null==sysPermission){
            log.error("传入 的 id:{}不合法",vo.getId());
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        BeanUtils.copyProperties(vo,sysPermission);
        sysPermission.setUpdateTime(new Date());
        int count=sysPermissionMapper.updateByPrimaryKeySelective(sysPermission);
        if(count!=1){
            throw new BusinessException(BaseResponseCode.OPERATION_ERROR);
        }
    }

    @Override
    public PageVO<SysPermission> pageInfo(PermissionPageReqVO vo) {
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        List<SysPermission> sysRoles =sysPermissionMapper.selectAll(vo);
        return PageUtil.getPageVO(sysRoles);
    }
}
