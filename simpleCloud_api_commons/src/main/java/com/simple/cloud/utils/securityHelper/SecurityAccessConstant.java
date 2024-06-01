package com.simple.cloud.utils.securityHelper;

/**
 * @author Charles
 * @create 2024-05-10-18:32
 */
public class SecurityAccessConstant {
    public static String TOKEN_PREFIX = "Bearer ";
    public static String HEADER_NAME_TOKEN = "Authorization";
    public static String TOKEN_TYPE = "Short-lived";
    public static String REFRESH_TOKEN_TYPE = "refresh";

    public static String WEB_REQUEST_TO_AUTH_URL = "http://127.0.0.1:10001";
    public static String REQUEST_LOGGING_URI = "/simple/cloud/access/login";
    public static String REQUEST_REFRESH = "/simple/cloud/access/refresh";

    public static String USERINFO_REDIS_STORAGE_KEY = "_INFO_dbh9";
    public static String REFRESH_TOKEN_REDIS_STORAGE_KEY = "_REFRESH_s9k1";
}
