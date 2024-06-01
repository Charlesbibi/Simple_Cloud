package com.simple.cloud.mapper;

import com.simple.cloud.entities.SysMenu;
import com.simple.cloud.entities.SysRoleMenu;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysRoleMenuMapper extends Mapper<SysRoleMenu> {

    //根据id获取角色id集合
    List<Long> getAssignById(Long id);

    // 修改角色-菜单的对应关系 - 删除原来的
    void deleteRoleMenu(@Param("id") Long id, @Param("originalIdList") List<Long> originalIdList);

    // 修改角色-菜单的对应关系 - 新增新的
    void reAssignRoleMenu(@Param("id") Long id, @Param("idList") List<Long> idList);
}