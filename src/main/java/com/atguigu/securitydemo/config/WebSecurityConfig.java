package com.atguigu.securitydemo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 配置类
//@EnableWebSecurity // 开启spring security自定义配置(springboot项目可省略)
public class WebSecurityConfig {
//    @Bean
//    public UserDetailsService userDetailsService() { // 基于内存的用户信息管理器
//        // 创建基于内存的用户信息管理器
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//
//        // 使用InMemoryUserDetailsManager来管理我们的UserDetails类型对象
//        manager.createUser(
//                // 创建UserDetails类型对象, 用于管理用户名和密码, 角色, 权限等内容
//                User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build()
//        );
//        return manager;
//    }

//    @Bean // 还有种方法来配置这个Bean, 因为我们只返回manager, 可以直接在DBUserDetailsManager这个类上添加@Component注解即可
//    public UserDetailsService userDBDetailsService() { // 基于数据库的用户信息管理器
//        // 创建基于数据库的用户信息管理器
//        return new DBUserDetailsManager();
//    }

    // springboot security的默认认证行为
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 开启授权保护
        http.authorizeHttpRequests(
                authorize -> authorize
                                // 对所有请求开启授权保护
                                .anyRequest()
                                // 已认证的请求会被自动授权
                                .authenticated()
                )
                // 自动表单认证, 也就是生成默认的登录(和登出)表单
                .formLogin(form -> {
                    form.loginPage("/login").permitAll();// 无需授权即可访问当前页面
                });
                // 基本授权方式: 没有表单授权方式后, 使用该方式会已alert的形式输入用户登录信息, 这种方式没有登出页面, 要么自己写, 要么清缓存, 这种方式没卵用
//                .httpBasic(Customizer.withDefaults());
        // 关闭针对post请求的csrf保护
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
