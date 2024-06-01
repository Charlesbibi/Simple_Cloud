package com.simple.cloud.entities.vo;


import lombok.Data;

import java.util.List;


@Data
public class AssginRoleVo {

    private Long userId;

    private List<Long> roleIdList;

}
