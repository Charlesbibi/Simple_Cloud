package com.simple.cloud.service.impl;

import com.simple.cloud.mapper.StorageMapper;
import com.simple.cloud.service.StorageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author Charles
 * @create 2024-04-23-17:16
 */

@Service
public class StorageServiceImpl implements StorageService {
    @Resource
    private StorageMapper storageMapper;

    @Override
    public void updateStorage(Long productId, Integer count) {
        storageMapper.updateStorage(productId, count);
    }
}
