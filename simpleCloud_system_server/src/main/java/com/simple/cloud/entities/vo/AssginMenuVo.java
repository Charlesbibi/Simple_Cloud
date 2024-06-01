package com.simple.cloud.entities.vo;

import lombok.Data;

import java.util.List;


@Data
public class AssginMenuVo {

    private Long roleId;

    private List<Long> menuIdList;

}
