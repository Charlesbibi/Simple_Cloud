package com.simple.cloud.controller;

import com.alibaba.nacos.api.model.v2.Result;
import com.simple.cloud.entities.SysMenu;
import com.simple.cloud.service.SysMenuService;
import com.simple.cloud.service.SysRoleService;
import com.simple.cloud.utils.ResultCodeEnum;
import com.simple.cloud.utils.ResultData;
import jakarta.annotation.Resource;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Charles
 * @create 2024-04-30-22:28
 */

@RestController
@CrossOrigin
@RequestMapping("/system/sysMenu")
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;
    @Resource
    private SysRoleService sysRoleService;

    /**
     * 根据id删除菜单
     * */
    @DeleteMapping("/delete/{id}")
    public ResultData deleteById(@PathVariable("id") Long id){
        boolean isDeleted = sysMenuService.removeMenuById(id);

        if(isDeleted)
            return ResultData.success("删除成功");

        return ResultData.fail(ResultCodeEnum.RC998.getCode(), "删除失败，请联系管理员");
    }

    /**
     * 获取菜单的树形结构
     * */
    @GetMapping("/getNodeList")
    public ResultData getNodeList(){
        List<SysMenu> sysMenus = sysMenuService.getAll();
        //转换为树形结构
        List<SysMenu> nodeList = sysMenuService.getNode(sysMenus);

        if(!CollectionUtils.isEmpty(nodeList))
            return ResultData.success(nodeList);
        else
            return ResultData.fail(ResultCodeEnum.RC500.getCode(), "当前没有菜单");
    }

    /**
     * 更新菜单
     * */
    @PutMapping("/doAssign/{id}")
    public Result doAssign(@PathVariable("id") Long id,
                           @RequestBody List<Long> idList){
        //获取给该角色分配的menu idList
        List<Long> originalIdList = sysRoleService.getAssign(id);

        if(!originalIdList.isEmpty()){
            //删除
            sysMenuService.deleteRoleMenu(id, originalIdList);
        }

        //重新添加
        sysMenuService.reAssignRoleMenu(id, idList);

        return Result.success("修改成功");
    }

    /**根据roleID获取到 按钮权限*/
    @GetMapping("/getAssign/{id}")
    public Result getAssignByRoleId(@PathVariable("id") Long id){
        //通过id获取到完整的菜单列表
        List<SysMenu> roleMenuList = sysMenuService.getAllMenuByRoleId(id);

        List<SysMenu> node = sysMenuService.getNode(roleMenuList);
        List<Long> subList = getSubIdList(node,new ArrayList<Long>());

        return Result.success(subList);
    }

    // 获取node中每个节点的子按钮
    private List<Long> getSubIdList(List<SysMenu> roleMenuList,List<Long> subList) {
        for(SysMenu sysMenu : roleMenuList){
            if(sysMenu.isSelect())
                continue;

            List<SysMenu> children = sysMenu.getChildren();
            if(CollectionUtils.isEmpty(children)) {
                subList.add(sysMenu.getId());
                sysMenu.setSelect(true);
            }
            else
                subList = getSubIdList(children,subList);
        }
        return subList;
    }
}
