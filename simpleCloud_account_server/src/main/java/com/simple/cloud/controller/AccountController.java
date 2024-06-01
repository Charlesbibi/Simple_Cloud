package com.simple.cloud.controller;

import com.simple.cloud.service.AccountService;
import com.simple.cloud.utils.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Charles
 * @create 2024-04-23-16:55
 */

@RestController
public class AccountController {
    @Resource
    private AccountService accountService;

    @PostMapping("/feign/account/updateAccountMoney")
    public ResultData updateAccountMoney(@RequestParam("userId") Long userId,
                                         @RequestParam("money") Long money){
        accountService.updatePrice(userId,money);

        return ResultData.success("修改账户金额成功");
    }
}
