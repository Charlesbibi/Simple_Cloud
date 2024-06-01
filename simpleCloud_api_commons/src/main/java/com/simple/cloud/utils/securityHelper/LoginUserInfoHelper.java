package com.simple.cloud.utils.securityHelper;

/**
 * @author Charles
 * @create 2024-04-30-23:33
 */
public class LoginUserInfoHelper {

    private static ThreadLocal<Long> userId = new ThreadLocal<Long>();
    private static ThreadLocal<String> username = new ThreadLocal<String>();
    private static ThreadLocal<String> email = new ThreadLocal<String>();

    public static String getEmail() {
        return email.get();
    }

    public static void setEmail(String _email) {
        email.set(_email);
    }
    public static void removeEmail() {
        email.remove();
    }

    public static void setUserId(Long _userId) {
        userId.set(_userId);
    }
    public static Long getUserId() {
        return userId.get();
    }
    public static void removeUserId() {
        userId.remove();
    }
    public static void setUsername(String _username) {
        username.set(_username);
    }
    public static String getUsername() {
        return username.get();
    }
    public static void removeUsername() {
        username.remove();
    }
}
