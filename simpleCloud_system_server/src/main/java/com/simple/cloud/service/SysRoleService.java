package com.simple.cloud.service;

import com.simple.cloud.entities.SysRole;
import com.simple.cloud.entities.SysRole;

import java.util.List;

/**
 * @author Charles
 * @create 2024-04-30-22:18
 */
public interface SysRoleService {

    int addRole(SysRole sysRole);

    int updateRole(SysRole sysRole);

    int deleteRole(Long id);

    List<SysRole> findAllRoles();

    // 获取当前的所有权限
    List<Long> getAssign(Long id);

    // 根据roleId 和 menuId删除对应关系
    void deleteRoleMenu(Long id, List<Long> originalIdList);
}
