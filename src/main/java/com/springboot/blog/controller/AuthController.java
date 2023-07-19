package com.springboot.blog.controller;

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
@RequestMapping("/api/auth")
public class AuthController {

    private AuthServices authservice;


    public AuthController(AuthServices authservice) {
        this.authservice = authservice;
    }

    //Build login Rest Api
    @PostMapping(value = {"/login","/sign-in"})
    public ResponseEntity<String> login(@RequestBody LoginDto logindto){
        String response = authservice.Login(logindto);
        return ResponseEntity.ok(response);
    }

    //Build register Rest Api
    @PostMapping(value = {"/register","/signup"})
    public  ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authservice.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
