package com.simple.cloud.utils;

import com.simple.cloud.entities.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Charles
 * @create 2024-04-30-23:59
 */
public class MenuHelper {

    /**
     * 使用递归方法建菜单
     * @param sysMenuList
     * @return
     */
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList){
        List<SysMenu> trees = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            if (sysMenu.getParentId().longValue() == 0) {
                trees.add(findChildren(sysMenu,sysMenuList));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> treeNodes) {
        // sysMenu.setChildren(new ArrayList<SysMenu>());
        //判断当前Menu的parentId值是否是sysMenu的id值
        for (SysMenu it : treeNodes) {
            if(sysMenu.getId().longValue() == it.getParentId().longValue() && it.getIsDeleted() == 0) {
                //判断当前孩子列表是否存在
                if (sysMenu.getChildren() == null) {
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return sysMenu;
    }
}
