package com.springboot.blog.controller;

import com.springboot.blog.payload.JwtAuthResponse;
import com.springboot.blog.payload.LoginDto;
import com.springboot.blog.payload.RegisterDto;
import com.springboot.blog.service.AuthServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthServices authservice;


    public AuthController(AuthServices authservice) {
        this.authservice = authservice;
    }

    //Build login Rest Api
    @PostMapping(value = {"/login","/sign-in"})
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto logindto){
        String token = authservice.Login(logindto);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }

    //Build register Rest Api
    @PostMapping(value = {"/register","/signup"})
    public  ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authservice.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
