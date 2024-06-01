package com.simple.cloud.mapper;

import com.simple.cloud.entities.SysRole;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysRoleMapper extends Mapper<SysRole> {

    // 查询用户所有的菜单列表
    List<SysRole> selectByRoleIds(List<Long> roleIDs);

    //根据id所有角色查询
    List<SysRole> selectAllByUserId(Long id);
}