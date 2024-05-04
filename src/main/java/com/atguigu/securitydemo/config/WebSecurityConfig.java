package com.atguigu.securitydemo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration // 配置类
//@EnableWebSecurity // 开启spring security自定义配置(springboot项目可省略)
public class WebSecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        // 创建基于内存的用户信息管理器
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        // 使用InMemoryUserDetailsManager来管理我们的UserDetails类型对象
        manager.createUser(
                // 创建UserDetails类型对象, 用于管理用户名和密码, 角色, 权限等内容
                User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build()
        );
        return manager;
    }
}
