package com.simple.cloud.filter;

import com.simple.cloud.utils.ResponseUtil;
import com.simple.cloud.utils.ResultCodeEnum;
import com.simple.cloud.utils.ResultData;
import com.simple.cloud.utils.securityHelper.JWTHelper;
import com.simple.cloud.utils.securityHelper.LoginUserInfoHelper;
import com.simple.cloud.utils.securityHelper.SecurityAccessConstant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Charles
 * @create 2024-05-08-14:09
 *
 * 认证解析token过滤器
 */
@Order(1)
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    public TokenAuthenticationFilter() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.info("uri:"+request.getRequestURI());

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        if(null != authentication) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } else {
            ResponseUtil.out(response, ResultData.fail(ResultCodeEnum.RC401.getCode(), ResultCodeEnum.RC401.getMessage()));
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        //请求头是否有token
        String token = JWTHelper.getToken(request.getHeader(SecurityAccessConstant.HEADER_NAME_TOKEN));

        if(null != token) {
            String email = JWTHelper.getEmail(token);
            Long userId = JWTHelper.getUserId(token);
            List<String> permission = JWTHelper.getPermission(token);

            if(null != permission) {
                //当前用户信息放到ThreadLocal里面
                LoginUserInfoHelper.setUserId(userId);
                LoginUserInfoHelper.setEmail(email);

                //把权限数据转换要求集合类型 List<SimpleGrantedAuthority>
                List<SimpleGrantedAuthority> collect = permission.stream().map(val -> new SimpleGrantedAuthority(val)).collect(Collectors.toList());

                return new UsernamePasswordAuthenticationToken(email, null, collect);
            }

        }
        return null;
    }
}