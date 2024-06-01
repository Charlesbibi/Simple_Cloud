package com.simple.cloud.mapper;

import com.simple.cloud.entities.Storage;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface StorageMapper extends Mapper<Storage> {

    /*
    * 更新库存
    * */
    void updateStorage(@Param("productId") Long productId, @Param("count") Integer count);
}