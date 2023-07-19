package com.springboot.blog.service;

import com.springboot.blog.payload.LoginDto;
import com.springboot.blog.payload.RegisterDto;

public interface AuthServices {

    String Login(LoginDto logindto);

    String register(RegisterDto registerDto);
}
