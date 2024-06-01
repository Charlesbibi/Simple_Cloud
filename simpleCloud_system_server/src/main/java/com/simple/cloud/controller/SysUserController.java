package com.simple.cloud.controller;

import com.simple.cloud.entities.SysRole;
import com.simple.cloud.entities.SysUser;
import com.simple.cloud.service.SysRoleService;
import com.simple.cloud.service.SysUserService;
import com.simple.cloud.utils.ResultCodeEnum;
import com.simple.cloud.utils.ResultData;
import com.simple.cloud.utils.securityHelper.SHA_256Helper;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Charles
 * @create 2024-04-30-22:28
 */

@RestController
@CrossOrigin
@RequestMapping("/system/sysUser")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysRoleService sysRoleService;


    /**
     * 为用户分配角色（1用户 - n角色）
     * */
    @PostMapping("/assignRoles/{id}")
    public ResultData assignRoles(@PathVariable("id") Long uid,
                                 @RequestBody List<Long> roleIDs){
        List<Long> originalIdList = (List<Long>) getAllUserRole(uid).getData();

        if(!originalIdList.isEmpty()){
            //删除一开始的
            sysUserService.assignRole(uid,originalIdList);
        }

        // 重新添加
        sysUserService.reAssignRole(uid , roleIDs);

        return ResultData.success("修改成功");
    }

    /**
     *  根据用户id获取所有的角色id（可能对应多个角色）
     * */
    @GetMapping("/getAllUserRole/{id}")
    public ResultData getAllUserRole(@PathVariable("id") Long id){
        List<Long> allRoleIdLists = sysUserService.getAllRole(id);

        if(allRoleIdLists == null)
            return ResultData.fail(ResultCodeEnum.RC500.getCode(), "无法查询指定用户的角色，请联系管理员");

        //使用stream去重
        allRoleIdLists = allRoleIdLists.stream().distinct().collect(Collectors.toList());
        return ResultData.success(allRoleIdLists);
    }

    /**
     *  新增用户
     * */
    @PostMapping("/add")
    public ResultData addUser(@RequestBody SysUser sysUser){
        String encrypt = SHA_256Helper.encrypt(sysUser.getPassword());
        sysUser.setPassword(encrypt);
        sysUser.setId(null);

        int count = sysUserService.addUser(sysUser);

        if(count > 0)
            return ResultData.success("插入成功");

        return ResultData.fail(ResultCodeEnum.RC999.getCode(), "插入失败，请联系管理员");
    }

    /**
     * 修改用户
     * */
    @PutMapping("/update")
    public ResultData updateUser(@RequestBody SysUser sysUser){
        int count = sysUserService.updateUser(sysUser);

        if(count > 0)
            return ResultData.success("修改成功");

        return ResultData.fail(ResultCodeEnum.RC997.getCode(), "修改失败，请联系管理员");
    }

    /**
     * 删除指定id用户
     * */
    @DeleteMapping("/delete/{id}")
    public ResultData deleteUser(@PathVariable("id") Long id){
        int count = sysUserService.deleteUser(id);

        if(count > 0)
            return ResultData.success("删除成功");

        return ResultData.fail(ResultCodeEnum.RC998.getCode(), "删除失败，请联系管理员");
    }

    /**
     * 获取所有用户列表
     * */
    @GetMapping("/listAll")
    @PreAuthorize("hasAuthority('bnt.sysUser.list')")
    public ResultData getAllUser(){
        List<SysUser> sysUsers = sysUserService.findAllUsers();

        if(sysUsers != null){
            return ResultData.success(sysUsers);
        }

        return ResultData.fail(ResultCodeEnum.RC996.getCode(), "查询失败，请联系管理员");
    }
}
