package com.simple.cloud.utils.securityHelper;

import com.alibaba.csp.sentinel.util.StringUtil;
import io.jsonwebtoken.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Charles
 * @create 2024-04-30-23:36
 */
public class JWTHelper {

    private static long tokenExpiration = 10 * 60 * 1000;
    private static long tokenRefreshExpiration = 12 * 60 * 60 * 1000;
    private static String tokenSignKey = "31c78b41f";

    private static String buildToken(String type, Long userId, String email, List<String> permission , long timeToLive){
        return Jwts.builder()
                .setSubject("AUTH-USER")
                .setExpiration(new Date(System.currentTimeMillis() + timeToLive))
                //.claim("token_type", type)
                .claim("userId", userId)
                .claim("email", email)
                .claim("permission", permission)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    public static String[] createToken(Long userId, String email, List<String> permission) {
        String token = buildToken(SecurityAccessConstant.TOKEN_TYPE, userId,email,permission,tokenExpiration);
        //token过期时可以刷新长期token
        String refreshToken = buildToken(SecurityAccessConstant.REFRESH_TOKEN_TYPE, userId,email,permission,tokenRefreshExpiration);

        return new String[]{token , refreshToken};
    }

    // 原始token过期时刷新token 而refreshToken保持不变(如果refresh都过期则需重新登录)
    public static String refresh(String refreshToken){
        return buildToken(SecurityAccessConstant.TOKEN_TYPE, getUserId(refreshToken) ,getEmail(refreshToken)
                , getPermission(refreshToken) , tokenExpiration);
    }

    // 去掉前缀
    public static String getToken(String token){
        if(token == null)
            return null;

        if(token.startsWith(SecurityAccessConstant.TOKEN_PREFIX))
            return token.replace(SecurityAccessConstant.TOKEN_PREFIX,"");

        //没带前缀的认为是无效token
        return null;
    }

    // 获取当前token过期时间
    public static Date getExpirationDate(String token) {
        if(StringUtil.isBlank(token))
            return null;

        Claims claims = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody();
        return claims.getExpiration();
    }

    //判断当前token是否过期
    public static boolean isOutDate(String token){
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Date expirationDate = claimsJws.getBody().getExpiration();
            return expirationDate.before(new Date());
        } catch (JwtException e) {
            // JWT token无效或已损坏
            return true;
        }
    }

//    //获取token类型 短期还是refresh
//    public static String getTokenType(String token) {
//        try {
//            if (token == null || token == "") return null;
//
//            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
//            Claims claims = claimsJws.getBody();
//            return (String) claims.get("token_type");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public static Long getUserId(String token) {
        try {
            if (token == null || token == "") return null;

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            Integer userId = (Integer) claims.get("userId");
            return userId.longValue();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getEmail(String token) {
        try {
            if (token == null || token == "") return "";

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (String) claims.get("email");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> getPermission(String token) {
        try {
            if (token == null || token == "") return null;

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (List<String>) claims.get("permission");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String[] token = createToken(1L, "admin@213.com", null);
        System.out.println(Arrays.toString(token));
        System.out.println(getExpirationDate(token[0]).getTime());
    }
}
