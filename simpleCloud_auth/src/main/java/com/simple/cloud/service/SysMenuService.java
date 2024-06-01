package com.simple.cloud.service;

import com.simple.cloud.entities.SysMenu;
import com.simple.cloud.entities.vo.RouterVo;

import java.util.List;

/**
 * @author Charles
 * @create 2024-04-30-22:18
 */
public interface SysMenuService {
    //根据id获取所有按钮列表
    List<String> getAllMenuListByUserId(Long userId);

    //根据id获取所有菜单列表
    List<RouterVo> getAllRouterListByUserId(Long userId);
}
