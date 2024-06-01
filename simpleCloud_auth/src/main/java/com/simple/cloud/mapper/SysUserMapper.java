package com.simple.cloud.mapper;

import com.simple.cloud.entities.SysUser;
import tk.mybatis.mapper.common.Mapper;

public interface SysUserMapper extends Mapper<SysUser> {

    // 根据email获取用户
    SysUser getUserByEmail(String email);
}