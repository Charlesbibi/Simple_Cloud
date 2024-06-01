package com.simple.cloud.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 表名：system_user
*/
@ToString
@Getter
@Setter
@Data
public class SysUser implements Serializable {
    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**邮箱*/
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话
     */
    private String phone;

    /**
     * 姓名
     */
    private String name;

    /**
     * 头像
     * */
    private String headUrl;

    /**
     * 用户状态（0：禁用，1：正常）
     * */
    private Byte status;

    /**
     * 是否删除（0：不是，1：删除）
     */
    private Byte isDeleted;

    private transient List<SysRole> roleList;
}