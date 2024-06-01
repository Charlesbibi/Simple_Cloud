package com.simple.cloud.config;

import com.simple.cloud.feign.interceptor.FeignAuthInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Charles
 * @create 2024-05-07-16:35
 */

@Configuration
public class FeignConfig{
    /**
     * feign 调用丢失token解决方案，新增拦截器
     * @return
     */
    @Bean
    public RequestInterceptor requestInterceptor(){
        return new FeignAuthInterceptor();
    }
}
