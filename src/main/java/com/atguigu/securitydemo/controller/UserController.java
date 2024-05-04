package com.atguigu.securitydemo.controller;

import com.atguigu.securitydemo.entity.User;
import com.atguigu.securitydemo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    public UserService userService;

    @GetMapping("/list")
    public List<User> getUsers() {
        return userService.list();
    }
}
