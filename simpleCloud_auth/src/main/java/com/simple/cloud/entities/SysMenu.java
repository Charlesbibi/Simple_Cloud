package com.simple.cloud.entities;

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
 * 表名：system_menu
*/
@Table(name = "system_menu")
@ToString
@Getter
@Setter
public class SysMenu implements Serializable {
    /**
     * 菜单id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 所属上级
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 类型（0：目录，1：菜单，2：按钮）
     */
    private Byte type;

    /**
     * 名称
     */
    private String name;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 路由路径
     */
    private String component;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    @Column(name = "sort_value")
    private Integer sortValue;

    /**
     * 状态（0：禁止，1：正常）
     */
    private Byte status;

    /**
     * 是否删除（0：不是，1：删除）
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;

    // 下级列表
    private transient List<SysMenu> children;

    //是否选中
    private transient boolean isSelect;
}