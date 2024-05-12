package com.atguigu.securitydemo.service.impl;

import com.atguigu.securitydemo.config.DBUserDetailsManager;
import com.atguigu.securitydemo.entity.User;
import com.atguigu.securitydemo.mapper.UserMapper;
import com.atguigu.securitydemo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private DBUserDetailsManager userDetailsManager;

    @Override
    public void saveUserDetails(User user) {
        userDetailsManager.createUser(user);
    }
}
