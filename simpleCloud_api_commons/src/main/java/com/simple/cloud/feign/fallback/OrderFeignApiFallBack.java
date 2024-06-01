package com.simple.cloud.feign.fallback;


import com.simple.cloud.entities.Order;
import com.simple.cloud.feign.OrderFeignApi;
import com.simple.cloud.utils.ResultCodeEnum;
import com.simple.cloud.utils.ResultData;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Charles
 * @create 2024-04-25-21:19
 */
@Component
public class OrderFeignApiFallBack implements OrderFeignApi {
    @Override
    public int insertOrder(Order order) {
        return -1;
    }

    @Override
    public ResultData getAll() {
        return ResultData.fail(ResultCodeEnum.RC201.getCode(),"联系管理员中...请稍后再试");
    }

    @Override
    public ResultData deleteById(Long id) {
        return ResultData.fail(ResultCodeEnum.RC201.getCode(),"联系管理员中...请稍后再试");
    }
}
