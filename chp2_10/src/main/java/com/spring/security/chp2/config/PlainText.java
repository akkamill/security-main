package com.spring.security.chp2.config;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PlainText implements PasswordEncoder {

    private PlainText() {
    }

    public String encode(CharSequence rawPassword) {
        System.out.println(".. " + rawPassword);
        return new StringBuilder(rawPassword).reverse().toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return new StringBuilder(rawPassword).reverse().toString().equals(encodedPassword);
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return PasswordEncoder.super.upgradeEncoding(encodedPassword);
    }
/*
singleton

 */
    public static PasswordEncoder getInstance() {
        return INSTANCE;
    }

    private static final PasswordEncoder INSTANCE = new PlainText();
}
