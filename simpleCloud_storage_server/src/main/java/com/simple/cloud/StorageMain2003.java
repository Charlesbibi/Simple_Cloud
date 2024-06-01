package com.simple.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Charles
 * @create 2024-04-26-14:49
 */

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.simple.cloud.mapper")
public class StorageMain2003 {
    public static void main(String[] args) {
        SpringApplication.run(StorageMain2003.class, args);
    }
}
