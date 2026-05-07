package com.ws101.Tan.EcommerceApi.controller;

import com.ws101.Tan.EcommerceApi.entity.User;
import com.ws101.Tan.EcommerceApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {

        return userService.saveUser(user);
    }
}