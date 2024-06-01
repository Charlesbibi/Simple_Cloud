package com.simple.cloud.feign;

import com.simple.cloud.entities.Order;
import com.simple.cloud.feign.fallback.OrderFeignApiFallBack;
import com.simple.cloud.utils.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Charles
 * @create 2024-04-25-21:20
 */

@FeignClient(value = "seata-order-service",fallback = OrderFeignApiFallBack.class)
public interface OrderFeignApi {

    @PostMapping("/order/insertOrder")
    public int insertOrder(@RequestBody Order order);

    @GetMapping("/order/getAll")
    public ResultData getAll();

    @DeleteMapping("/order/delete/{id}")
    public ResultData deleteById(@PathVariable("id") Long id);
}
