package com.simple.cloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Charles
 * @create 2024-04-23-16:17
 */

@FeignClient(value = "seata-storage-service")
public interface StorageFeignApi {

    /*
    *  扣减库存
    * */
    @PostMapping("/feign/storage/updateStorage")
    void updateStorage(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
