package com.simple.cloud.mapper;

import com.simple.cloud.entities.SysMenu;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysMenuMapper extends Mapper<SysMenu> {

    //获取所有的菜单
    List<SysMenu> getAllMenu();

    //获取所有菜单
    List<SysMenu> getAllMenuById(Long userId);
}