package com.simple.cloud.service.impl;

import com.simple.cloud.mapper.AccountMapper;
import com.simple.cloud.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author Charles
 * @create 2024-04-23-16:54
 */

@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountMapper accountMapper;

    @Override
    public void updatePrice(Long userId, Long money) {
        accountMapper.updateAccountMoney(userId,money);
    }
}
