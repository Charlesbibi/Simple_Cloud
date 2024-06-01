package com.simple.cloud.mapper;

import com.simple.cloud.entities.SysUserRole;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysUserRoleMapper extends Mapper<SysUserRole> {
    //角色查询
    List<Long> getAllRoleIDbyUserId(Long userId);

    //角色分配 - 删除原先
    void doAssign(@Param("uid") Long uid, @Param("ridLists") List<Long> ridLists);

    //角色分配 - 新增角色
    void reDoAssign(@Param("uid") Long uid,@Param("roleIdList") List<Long> roleIdList);
}