package com.simple.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.simple.cloud.entities.Order;
import com.simple.cloud.service.OrderService;
import com.simple.cloud.utils.ResultCodeEnum;
import com.simple.cloud.utils.ResultData;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Charles
 * @create 2024-04-23-16:38
 */

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/order/insertOrder")
    @SentinelResource(value = "insertOrder",blockHandler = "handlerBlockHandler")
    public int insertOrder(@RequestBody Order order){
        order.setId(null);
        return orderService.createOrder(order);
    }

    private ResultData handlerBlockHandler(@RequestBody Order order,
                                           BlockException ex){
        return ResultData.fail(ResultCodeEnum.RC500.getCode(), "流量过载触发限流，请稍后再试");
    }

    @GetMapping("/order/getAll")
    public ResultData getAll(){
        List<Order> all = orderService.getAll();

        if(all != null)
            return ResultData.success(all);

        return  ResultData.fail(ResultCodeEnum.RC996.getCode(),
                ResultCodeEnum.RC996.getMessage());
    }

    @DeleteMapping("/order/delete/{id}")
    public ResultData deleteById(@PathVariable("id") Long id) {
        int i = orderService.deleteById(id);

        if(i > 0)
            return ResultData.success(i);

        return  ResultData.fail(ResultCodeEnum.RC998.getCode(),
                ResultCodeEnum.RC998.getMessage());
    }
}
