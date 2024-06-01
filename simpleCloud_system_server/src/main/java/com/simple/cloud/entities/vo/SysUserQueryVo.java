//
//
package com.simple.cloud.entities.vo;


import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户查询实体
 * </p>
 */
@Data
public class SysUserQueryVo implements Serializable {
	
	private String keyword;
	private String phone;
	private String name;

	private Long roleId;
}

