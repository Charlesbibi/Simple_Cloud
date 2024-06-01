package com.simple.cloud.filter;


import com.simple.cloud.utils.ResponseUtils;
import com.simple.cloud.utils.ResultCodeEnum;
import com.simple.cloud.utils.ResultData;
import com.simple.cloud.utils.securityHelper.JWTHelper;
import com.simple.cloud.utils.securityHelper.SecurityAccessConstant;
import jakarta.annotation.Resource;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;


/**
 * @author Charles
 * @create 2024-05-10-17:20
 */
@Component
public class MyAccessFilter implements GlobalFilter, Ordered
{
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String uri = request.getURI().getPath();
        HttpMethod method = request.getMethod();

        // OPTION直接放行
        if(method.matches(HttpMethod.OPTIONS.name()))
            return chain.filter(exchange);

        //登录请求直接放行
        if(SecurityAccessConstant.REQUEST_LOGGING_URI.equals(uri) && method.matches(HttpMethod.POST.name()))
            return chain.filter(exchange);

        String token = JWTHelper.getToken(request.getHeaders().getFirst(SecurityAccessConstant.HEADER_NAME_TOKEN));
        if(null != token){

            //判断token是否过时
            if(!JWTHelper.isOutDate(token)){
                return chain.filter(exchange);

            }else{
//                if(!SecurityAccessConstant.REQUEST_REFRESH.equals(uri))    //当前不是刷新请求可以刷新
//                    return ResponseUtils.out(exchange , ResultData.fail(ResultCodeEnum.NEED_TO_REFRESH_TOKEN.getCode(),
//                        ResultCodeEnum.NEED_TO_REFRESH_TOKEN.getMessage()));

                //当前是刷新请求 但refreshToken都过期了，即刷新不支持
                return ResponseUtils.out(exchange , ResultData.fail(ResultCodeEnum.RC401.getCode(), ResultCodeEnum.RC401.getMessage()));
            }

        }
        
        return ResponseUtils.out(exchange , ResultData.fail(ResultCodeEnum.RC401.getCode(), ResultCodeEnum.RC401.getMessage()));
    }

    /**
     * 获取refresh token
     * 如果存在则无感刷新并重新发送请求
     * */
    private Mono<Void> refreshToken(ServerWebExchange exchange, GatewayFilterChain chain, String token) {
        String refreshToken = (String) redisTemplate.opsForValue().get(token);

        if(refreshToken == null)
            return ResponseUtils.out(exchange , ResultData.fail(ResultCodeEnum.RC401.getCode(), ResultCodeEnum.RC401.getMessage()));

        // 向认证服务器发送请求，获取新的token
        Mono<ResultData> newTokenMono = WebClient.create().get()
                .uri(buildUri(SecurityAccessConstant.WEB_REQUEST_TO_AUTH_URL+SecurityAccessConstant.REQUEST_REFRESH
                        , new String[]{"refreshToken", refreshToken}))
                .retrieve()
                .bodyToMono(ResultData.class);

        // 原子操作
        AtomicBoolean isPass = new AtomicBoolean(false);
        AtomicReference<ServerHttpRequest> newRequest = null;
        //订阅数据
        newTokenMono.subscribe(resultData -> {
            if(resultData.getCode() == "200"){
                 newRequest.set(exchange.getRequest().mutate().header(SecurityAccessConstant.HEADER_NAME_TOKEN,
                         SecurityAccessConstant.TOKEN_PREFIX + resultData.getData()).build());
                isPass.set(true);
            }

        }).dispose(); // 销毁资源

        if(isPass.get()){
            // 如果成功获取到资源（新token则发送新请求）
            return chain.filter(exchange.mutate().request(newRequest.get()).build());
        }

        return ResponseUtils.out(exchange , ResultData.fail(ResultCodeEnum.RC401.getCode(), ResultCodeEnum.RC401.getMessage()));
    }

    @Override
    public int getOrder() {
        //数值越小 优先级越高
        return Ordered.LOWEST_PRECEDENCE;
    }

    private URI buildUri(String URL , String[]... attribute){
        // 构建带有查询参数的URI
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL);


        for(String[] query : attribute)
            builder.queryParam(query[0], query[1]);

        // 创建URI对象
        return builder.build().toUri();
    }
}
