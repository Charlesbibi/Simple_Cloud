package com.simple.cloud.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Charles
 * @create 2024-05-10-20:42
 */
public class ResponseUtils {
    public static Mono<Void> out(ServerWebExchange exchange, ResultData r){
        // 将ResultData对象转换为JSON字符串，并设置为响应体
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] responseBody = new byte[0];
        try {
            responseBody = objectMapper.writeValueAsBytes(r);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        exchange.getResponse().setStatusCode(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
        exchange.getResponse().getHeaders().add("Content-Type", "application/json");

        return exchange.getResponse().writeWith(Mono.just(new DefaultDataBufferFactory().wrap(responseBody)));
    }

    /**
     *  访问 localhost:10001/auth/login 为例子
     * @param url http://localhost:10001 前缀
     * @param uri /auth/login 后边的路径名称
     * @param key,value 请求头中的键值对
     * @return
     */
    public static Mono<ResultData> webClientRequest(String url , String uri , String key , String value){
        WebClient webClient = WebClient.create(url);
        Mono<ResultData> response = webClient.get()
                .uri(uri)
                .header(key , value)
                .retrieve()
                .bodyToMono(ResultData.class);

        return response;
    }
}
