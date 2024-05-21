package com.atguigu.securitydemo.controller;

import com.atguigu.securitydemo.entity.User;
import com.atguigu.securitydemo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    public UserService userService;

    @GetMapping("/list")
    @PreAuthorize("hasRole('USER')") // 在用户调用这个方法前, 检查用户是否有ADMIN这个角色和用户名为admin,
    // 如果没有配置这个注解, 默认开启了方法-权限时, 这个方法可以访问.
    public List<User> getUsers() {
        return userService.list();
    }

    @PostMapping("/add")
//    @PreAuthorize("hasRole('ADMIN')  and authentication.name == 'admin'") // 在用户调用这个方法前, 检查用户是否有USER这个角色
    @PreAuthorize("hasAnyAuthority('USER_ADD')") // 如果用户拥有USER_ADD权限, 用户才可以访问这个方法
    public void addUser(@RequestBody User user) {
        userService.saveUserDetails(user);
    }
}
