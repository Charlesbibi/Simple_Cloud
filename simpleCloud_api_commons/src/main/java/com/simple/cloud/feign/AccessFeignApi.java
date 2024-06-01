package com.simple.cloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Charles
 * @create 2024-05-10-20:51
 */

@FeignClient(value = "auth-service")
public interface AccessFeignApi {

    /*
     * 认证token
     * */
    @PostMapping("/feign/simple/cloud/access/login")
    void isTokenValidation();
}
