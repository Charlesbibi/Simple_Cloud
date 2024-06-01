package com.simple.cloud.entities;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 表名：system_role
*/
@Table(name = "system_role")
@ToString
public class SysRole implements Serializable {
    /**
     * 角色id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 角色编码
     */
    @Column(name = "role_code")
    private String roleCode;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否删除（0：没删除，1：删除了）
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;

    /**
     * 获取角色id
     *
     * @return id - 角色id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置角色id
     *
     * @param id 角色id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取角色名称
     *
     * @return roleName - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取角色编码
     *
     * @return roleCode - 角色编码
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * 设置角色编码
     *
     * @param roleCode 角色编码
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取是否删除（0：没删除，1：删除了）
     *
     * @return isDeleted - 是否删除（0：没删除，1：删除了）
     */
    public Byte getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否删除（0：没删除，1：删除了）
     *
     * @param isDeleted 是否删除（0：没删除，1：删除了）
     */
    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }
}