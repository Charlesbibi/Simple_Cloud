package com.simple.cloud.mapper;

import com.simple.cloud.entities.SysUser;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysUserMapper extends Mapper<SysUser> {

    // 根据email获取用户
    SysUser getUserByEmail(String email);


    // 查询所有的用户并封装roleList
    List<SysUser> selectAllWrapRoleList();

    // 根据id逻辑删除
    int deleteByIsDeleted(Long id);
}