package com.simple.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Charles
 * @create 2024-05-10-16:53
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.simple.cloud.mapper")
public class AuthMain10001 {
    public static void main(String[] args) {
        SpringApplication.run(AuthMain10001.class , args);
    }
}
