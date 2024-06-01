package com.simple.cloud.service;

import com.simple.cloud.entities.SysRole;
import com.simple.cloud.entities.SysUser;

import java.util.List;
import java.util.Map;

/**
 * @author Charles
 * @create 2024-04-30-22:17
 */
public interface SysUserService {

    // 根据email查找指定人
    SysUser findUserByEmail(String email);

    //根据id所有角色查询
    List<SysRole> selectAllByUserId(Long id);
}
