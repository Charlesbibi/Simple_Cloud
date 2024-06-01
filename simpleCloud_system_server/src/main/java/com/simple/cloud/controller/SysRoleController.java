package com.simple.cloud.controller;

import com.simple.cloud.entities.SysRole;
import com.simple.cloud.service.SysRoleService;
import com.simple.cloud.utils.ResultCodeEnum;
import com.simple.cloud.utils.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Charles
 * @create 2024-04-30-22:29
 */

@RestController
@CrossOrigin
@RequestMapping("/system/sysRole")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;

    /**
     *  新增角色
     * */
    @PostMapping("/add")
    public ResultData addRole(@RequestBody SysRole sysRole){
        int count = sysRoleService.addRole(sysRole);

        if(count > 0)
            return ResultData.success("插入成功");

        return ResultData.fail(ResultCodeEnum.RC999.getCode(), "插入失败，请联系管理员");
    }

    /**
     * 修改角色
     * */
    @PutMapping("/update")
    public ResultData updateRole(@RequestBody SysRole sysRole){
        int count = sysRoleService.updateRole(sysRole);

        if(count > 0)
            return ResultData.success("修改成功");

        return ResultData.fail(ResultCodeEnum.RC997.getCode(), "修改失败，请联系管理员");
    }

    /**
     * 删除指定id角色
     * */
    @DeleteMapping("/delete/{id}")
    public ResultData deleteRole(@PathVariable("id") Long id){
        int count = sysRoleService.deleteRole(id);

        if(count > 0)
            return ResultData.success("删除成功");

        return ResultData.fail(ResultCodeEnum.RC998.getCode(), "删除失败，请联系管理员");
    }

    /**
     * 获取所有角色列表
     * */
    @GetMapping("/listAll")
    public ResultData getAllRole(){
        List<SysRole> sysRoles = sysRoleService.findAllRoles();

        if(sysRoles != null)
            return ResultData.success(sysRoles);

        return ResultData.fail(ResultCodeEnum.RC996.getCode(), "查询失败，请联系管理员");
    }
}
