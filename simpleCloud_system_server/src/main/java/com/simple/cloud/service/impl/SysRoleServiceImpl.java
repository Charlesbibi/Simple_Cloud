package com.simple.cloud.service.impl;

import com.simple.cloud.entities.SysRole;
import com.simple.cloud.mapper.SysRoleMapper;
import com.simple.cloud.mapper.SysRoleMenuMapper;
import com.simple.cloud.service.SysRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Charles
 * @create 2024-04-30-22:27
 */

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public int addRole(SysRole sysRole) {
        return sysRoleMapper.insertSelective(sysRole);
    }

    @Override
    public int updateRole(SysRole sysRole) {
        return sysRoleMapper.updateByPrimaryKeySelective(sysRole);
    }

    @Override
    public int deleteRole(Long id) {
        return sysRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<SysRole> findAllRoles() {
        return sysRoleMapper.selectAll();
    }

    /**获取当前的所有权限*/
    @Override
    public List<Long> getAssign(Long id) {
        return sysRoleMenuMapper.getAssignById(id);
    }

   /** 根据roleId 和 menuId删除对应关系*/
    @Override
    public void deleteRoleMenu(Long id, List<Long> originalIdList) {
        sysRoleMenuMapper.deleteRoleMenu(id, originalIdList);
    }
}
