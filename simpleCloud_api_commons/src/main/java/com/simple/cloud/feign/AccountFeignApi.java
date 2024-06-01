package com.simple.cloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Charles
 * @create 2024-04-23-16:13
 */

@FeignClient(value = "seata-account-service")
public interface AccountFeignApi {

    /*
    * id为userId的用户删除对应的money -- 购买成功
    * */
    @PostMapping("/feign/account/updateAccountMoney")
    void updateAccountMoney(@RequestParam("userId") Long userId, @RequestParam("money") Long money);

}
