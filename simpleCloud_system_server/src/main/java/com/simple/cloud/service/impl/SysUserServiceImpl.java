package com.simple.cloud.service.impl;

import com.simple.cloud.entities.SysRole;
import com.simple.cloud.entities.SysUser;
import com.simple.cloud.mapper.SysRoleMapper;
import com.simple.cloud.mapper.SysUserMapper;
import com.simple.cloud.mapper.SysUserRoleMapper;

import com.simple.cloud.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Charles
 * @create 2024-04-30-22:19
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    /**获取当前角色消息 姓名-电话*/
    @Override
    public Map<String, Object> getCurrentUser() {
        return null;
    }

    @Override
    public int addUser(SysUser sysUser) {
        return sysUserMapper.insertSelective(sysUser);
    }

    @Override
    public int updateUser(SysUser sysUser) {
        return sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    public int deleteUser(Long id) {
        return sysUserMapper.deleteByIsDeleted(id);
    }

    /**根据email查找指定人*/
    @Override
    public SysUser findUserByEmail(String email) {
        return sysUserMapper.getUserByEmail(email);
    }

    @Override
    public List<SysUser> findAllUsers() {
        return sysUserMapper.selectAllWrapRoleList();
    }

    /**根据id获取所有的角色*/
    @Override
    public List<Long> getAllRole(Long id) {
        return sysUserRoleMapper.getAllRoleIDbyUserId(id);
    }

    /**根据id所有角色查询*/
    @Override
    public List<SysRole> selectAllByUserId(Long id) {
        return sysRoleMapper.selectAllByUserId(id);
    }

    /**角色分配 - 删除原先角色*/
    @Override
    public void assignRole(Long uid, List<Long> ridLists) {
        sysUserRoleMapper.doAssign(uid, ridLists);
    }


    /**角色分配 - 重写入角色*/
    @Override
    public void reAssignRole(Long uid, List<Long> roleIdList) {
        sysUserRoleMapper.reDoAssign(uid, roleIdList);
    }

    /**根据id找到指定用户*/
    @Override
    public SysUser getOneById(Long userId) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);

        if(sysUser.getStatus() == 0 || sysUser.getIsDeleted() == 1)
            return null;

        return sysUser;
    }
}
