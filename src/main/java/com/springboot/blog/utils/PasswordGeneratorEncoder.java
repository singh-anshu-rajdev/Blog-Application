package com.springboot.blog.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGeneratorEncoder {
    public static void main(String[] args) {
        PasswordEncoder passwordencoder = new BCryptPasswordEncoder();
        System.out.println(passwordencoder.encode("admin"));
        System.out.println(passwordencoder.encode("anshu"));
    }
}
