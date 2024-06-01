package com.simple.cloud.entities;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 表名：system_role_menu
*/
@Table(name = "system_role_menu")
@ToString
public class SysRoleMenu implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "menu_id")
    private Long menuId;

    /**
     * 是否删除（0：没有删除，1：删除成功）
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return roleId
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * @return menuId
     */
    public Long getMenuId() {
        return menuId;
    }

    /**
     * @param menuId
     */
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    /**
     * 获取是否删除（0：没有删除，1：删除成功）
     *
     * @return isDeleted - 是否删除（0：没有删除，1：删除成功）
     */
    public Byte getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否删除（0：没有删除，1：删除成功）
     *
     * @param isDeleted 是否删除（0：没有删除，1：删除成功）
     */
    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }
}