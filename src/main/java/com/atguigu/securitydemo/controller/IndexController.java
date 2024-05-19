package com.atguigu.securitydemo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {
    @GetMapping("/")
    public Map<Object, Object> hello() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object principal = authentication.getPrincipal(); // 身份信息
        Object credentials = authentication.getCredentials(); // 获取密码要做脱敏处理的(秘密等)
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities(); // 权限

        String name = authentication.getName();
        Map<Object, Object> result = new HashMap<>();
        result.put("username", name);
        result.put("authorities", authorities);

        return result;
    }
}
