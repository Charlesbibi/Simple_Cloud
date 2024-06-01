package com.simple.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Charles
 * @create 2024-04-26-15:02
 */

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMain9001.class, args);
    }
}
