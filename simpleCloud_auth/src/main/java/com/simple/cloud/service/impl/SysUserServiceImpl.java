package com.simple.cloud.service.impl;

import com.simple.cloud.entities.SysRole;
import com.simple.cloud.entities.SysUser;
import com.simple.cloud.mapper.SysRoleMapper;
import com.simple.cloud.mapper.SysUserMapper;
import com.simple.cloud.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;


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

    /**根据email查找指定人*/
    @Override
    public SysUser findUserByEmail(String email) {
        return sysUserMapper.getUserByEmail(email);
    }


    /**根据id所有角色查询*/
    @Override
    public List<SysRole> selectAllByUserId(Long id) {
        return sysRoleMapper.selectAllByUserId(id);
    }
}
