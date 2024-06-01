package com.simple.cloud.controller;
;
import com.simple.cloud.service.StorageService;
import com.simple.cloud.utils.ResultData;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Charles
 * @create 2024-04-23-17:17
 */

@RestController
@Slf4j
public class StorageController {

    @Resource
    private StorageService storageService;

    @PostMapping("/feign/storage/updateStorage")
    public ResultData updateStorage(@RequestParam("productId") Long productId, @RequestParam("count") Integer count){
        storageService.updateStorage(productId, count);

        return ResultData.success("成功修改库存");
    };
}
