package com.simple.cloud.utils.securityHelper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Charles
 * @create 2024-04-30-23:35
 */
@Component
public class CustomSHA_256PasswordEncoder implements PasswordEncoder {
    public String encode(CharSequence rawPassword) {
        return SHA_256Helper.encrypt(rawPassword.toString());
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(SHA_256Helper.encrypt(rawPassword.toString()));
    }
}