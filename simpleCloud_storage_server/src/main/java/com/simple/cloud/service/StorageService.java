package com.simple.cloud.service;

/**
 * @author Charles
 * @create 2024-04-23-17:15
 */
public interface StorageService {
    //更新库存
    void updateStorage(Long productId, Integer count);
}
