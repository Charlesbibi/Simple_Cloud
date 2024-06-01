package com.simple.cloud.service;


import com.simple.cloud.entities.Order;

import java.util.List;

/**
 * @author Charles
 * @create 2024-04-23-16:10
 */
public interface OrderService {

    /*创建订单*/
    int createOrder(Order order);

    /*查询所有订单*/
    List<Order> getAll();

    /*根据id删除订单*/
    int deleteById(Long id);
}
