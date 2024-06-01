package com.simple.cloud.service;

import com.simple.cloud.entities.SysMenu;
import com.simple.cloud.entities.vo.RouterVo;

import java.util.List;

/**
 * @author Charles
 * @create 2024-04-30-22:18
 */
public interface SysMenuService {
    //获取树形节点
    List<SysMenu> getNode(List<SysMenu> sysMenus);

    //删除节点
    boolean removeMenuById(Long id);

    //根据id获取所有按钮列表
    List<String> getAllMenuListByUserId(Long userId);

    //根据id获取所有菜单列表
    List<RouterVo> getAllRouterListByUserId(Long userId);

    //获取所有的菜单列表
    List<SysMenu> getAll();

    // 修改角色-菜单的对应关系 - 删除原来的
    void deleteRoleMenu(Long id, List<Long> originalIdList);

    // 修改角色-菜单的对应关系 - 新增新的
    void reAssignRoleMenu(Long id, List<Long> idList);

    // 根据roleId获取到对应的菜单列表
    List<SysMenu> getAllMenuByRoleId(Long id);
}
