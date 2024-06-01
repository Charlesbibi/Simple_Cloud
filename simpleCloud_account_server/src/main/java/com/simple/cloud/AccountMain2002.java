package com.simple.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Charles
 * @create 2024-04-26-14:55
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.simple.cloud.mapper")
public class AccountMain2002 {
    public static void main(String[] args) {
        SpringApplication.run(AccountMain2002.class, args);
    }
}
