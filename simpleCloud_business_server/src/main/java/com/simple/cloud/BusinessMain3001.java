package com.simple.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Charles
 * @create 2024-04-26-15:35
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class BusinessMain3001 {
    public static void main(String[] args) {
        SpringApplication.run(BusinessMain3001.class, args);
    }
}
