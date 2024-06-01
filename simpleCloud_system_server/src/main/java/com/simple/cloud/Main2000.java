package com.simple.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Charles
 * @create 2024-04-30-18:52
 */

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.simple.cloud.mapper")
public class Main2000 {
    public static void main(String[] args) {
        SpringApplication.run(Main2000.class,args);
    }
}
