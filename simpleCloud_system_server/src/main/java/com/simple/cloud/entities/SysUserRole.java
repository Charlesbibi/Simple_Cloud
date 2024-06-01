package com.simple.cloud.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 表名：system_user_role
*/
@Table(name = "system_user_role")
@ToString
@Getter
@Setter
@NoArgsConstructor
public class SysUserRole implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "user_id")
    private Long userId;

    /**
     * 是否删除（0：没有删除，1：删除了）
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;

    public SysUserRole(Long roleId, Long userId, Byte isDeleted){
        this.roleId = roleId;
        this.userId = userId;
        this.isDeleted = isDeleted;
    }
}