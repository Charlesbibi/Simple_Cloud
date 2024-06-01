package com.simple.cloud.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * 表名：system_user
*/
@Table(name = "system_user")
@ToString
@Getter
@Setter
@Data
public class SysUser implements Serializable {
    /**
     * 用户id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
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
     * 头像
     * */
    private String headUrl;

    /**
     * 用户状态（0：禁用，1：正常）
     * */
    private Byte status;

    /**
     * 姓名
     */
    private String name;

    /**
     * 是否删除（0：不是，1：删除）
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;

    private List<SysRole> roleList;
}