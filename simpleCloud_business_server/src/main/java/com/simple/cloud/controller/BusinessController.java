package com.simple.cloud.controller;

import com.simple.cloud.entities.Order;
import com.simple.cloud.feign.OrderFeignApi;
import com.simple.cloud.utils.ResultCodeEnum;
import com.simple.cloud.utils.ResultData;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;

/**
 * @author Charles
 * @create 2024-04-26-15:36
 */

@RestController
public class BusinessController {

    @Resource
    private OrderFeignApi orderFeignApi;

    @PostMapping("/business/gateway/order/buy")
    public ResultData buyOrder(@RequestBody Order order){
        int result = orderFeignApi.insertOrder(order);

        if(result == -1)
            return ResultData.fail(ResultCodeEnum.RC999.getCode(),
                    ResultCodeEnum.RC999.getMessage());

        return ResultData.success("插入订单成功 " + order.toString());
    }

    @GetMapping("/business/gateway/order/getAll")
    public ResultData<List<Order>> getAllOrder() {
        //FeignTokenHelper.passToken(RequestContextHolder.getRequestAttributes());

        ResultData res = orderFeignApi.getAll();

        if(res.getCode().equals(ResultCodeEnum.RC200.getCode()))
            return ResultData.success((List<Order>) res.getData());

        return res;
    }

    @DeleteMapping("/business/gateway/order/delete/{id}")
    public ResultData deleteOrderById(@PathVariable("id") Long id) {
        ResultData res = orderFeignApi.deleteById(id);

        if(res.getCode().equals(ResultCodeEnum.RC200.getCode()))
            return ResultData.success("删除成功");

        return res;
    }
}
