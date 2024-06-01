package com.simple.cloud.service.impl;

import com.simple.cloud.entities.Order;
import com.simple.cloud.feign.AccountFeignApi;
import com.simple.cloud.feign.StorageFeignApi;
import com.simple.cloud.mapper.OrderMapper;
import com.simple.cloud.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Charles
 * @create 2024-04-23-16:11
 */

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private AccountFeignApi accountFeignApi;
    @Resource
    private StorageFeignApi storageFeignApi;

    @Override
    @GlobalTransactional(name = "order-seata-TM", rollbackFor = Exception.class)
    public int createOrder(Order order) {
        //1.新增订单
        // 一开始为0，还在流转中
        order.setStatus(0);
        log.info(">>>>>>>>>开启插入订单>>>>>>>>>>>>>"+ RootContext.getXID());
        int insertSuccess = orderMapper.insertSelective(order);

        if(insertSuccess > 0){
            //插入成功
            log.info(">>>>>>>>>插入订单完成>>>>>>>>>>>>>");

            //2. 修改库存
            log.info(">>>>>>>>>开始修改库存>>>>>>>>>>>>>");
            storageFeignApi.updateStorage(order.getProductId(),order.getCount());
            log.info(">>>>>>>>>修改库存完成>>>>>>>>>>>>>");

            //3. 修改账户余额
            log.info(">>>>>>>>>开始修改账户余额>>>>>>>>>>>>>");
            accountFeignApi.updateAccountMoney(order.getUserId(), order.getMoney());
            log.info(">>>>>>>>>修改账户余额完成>>>>>>>>>>>>>");

            //4.订单完成记录 - 1
            order.setStatus(1);
            log.info(">>>>>>>>>开始修改订单状态>>>>>>>>>>>>>");
            orderMapper.updateByPrimaryKeySelective(order);
            log.info(">>>>>>>>>修改订单状态完成>>>>>>>>>>>>>");

        }

        log.info("==================>结束新建订单"+"\t");
        return insertSuccess;
    }

    @Override
    public List<Order> getAll() {
        return orderMapper.selectAll();
    }

    @Override
    public int deleteById(Long id) {
        return orderMapper.deleteByPrimaryKey(id);
    }
}
