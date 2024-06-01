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
    //获取当前角色消息 姓名-电话
    Map<String,Object> getCurrentUser();

    int addUser(SysUser sysUser);

    int updateUser(SysUser sysUser);

    int deleteUser(Long id);

    // 根据email查找指定人
    SysUser findUserByEmail(String email);

    // 查询所有
    List<SysUser> findAllUsers();

    //获取指定用户的角色
    List<Long> getAllRole(Long id);

    //根据id所有角色查询
    List<SysRole> selectAllByUserId(Long id);

    //角色分配 - 删除原先角色
    void assignRole(Long uid, List<Long> ridLists);

    //角色分配 - 重写入角色
    void reAssignRole(Long uid, List<Long> roleIdList);

    // 根据id获取指定用户
    SysUser getOneById(Long userId);
}
