package com.whl.zuhaowan.mapper;

import com.whl.zuhaowan.entity.FileClusterStore;

import java.util.List;

public interface FileClusterStoreMapper {
    int deleteByPrimaryKey(String id);

    int deleteByIds(List<String> ids);

    int insert(FileClusterStore record);

    int insertSelective(FileClusterStore record);

    FileClusterStore selectByPrimaryKey(String id);

    List<FileClusterStore> getListByIds(List<String> ids);

    int updateByPrimaryKeySelective(FileClusterStore record);

    int updateByPrimaryKey(FileClusterStore record);
}