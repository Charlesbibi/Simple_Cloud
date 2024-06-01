package com.simple.cloud.feign.interceptor;

import com.simple.cloud.utils.ThreadLocalUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @author Charles
 * @create 2024-05-07-16:44
 */
public class FeignAuthInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = ThreadLocalUtil.get("token");
        requestTemplate.header("token",token);
    }
}
