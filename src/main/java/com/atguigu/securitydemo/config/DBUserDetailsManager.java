package com.atguigu.securitydemo.config;

import com.atguigu.securitydemo.entity.User;
import com.atguigu.securitydemo.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class DBUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    // 向数据库中插入新的用户信息
    @Override
    public void createUser(UserDetails userDetails) {
        User user = new User();
        user.setUsername(userDetails.getUsername());
        user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        user.setEnabled(true);
        userMapper.insert(user);

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    /**
     * 通过用户名从数据库中获取用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        } else {
//            // 用户权限(角色)列表
//            Collection<GrantedAuthority> authorities = new ArrayList<>();
//            // 在这里获取用户信息, 同时获取用户的权限信息, 所以可以把权限信息存到数据库, 但是为了简单起见, 我们直接硬编码在这里了, 直接为所有登录的用户赋权限
//            authorities.add((GrantedAuthority) () -> "USER_LIST"); // 这里直接匿名内部类了
//            authorities.add((GrantedAuthority) () -> "USER_ADD"); // 这里直接匿名内部类了
//            return new org.springframework.security.core.userdetails.User(
//                    user.getUsername(),
//                    user.getPassword(),
//                    user.isEnabled(),
//                    true,  //用户账号是否过期
//                    true, // 用户凭证是否过期
//                    true, // 用户是否未被锁定
//                    authorities // 权限列表
//            );

            // 角色配置
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .disabled(!user.isEnabled()) // 用户是否禁用方法
                    .credentialsExpired(false) // 是否过期方法: 未过期
                    .accountLocked(false) // 用户是否被锁定
                    .roles("USER") // 必须是ADMIN角色才可以访问/user/**资源
                    .build();
        }
    }
}
