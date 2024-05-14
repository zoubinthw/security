package com.atguigu.securitydemo.config;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Object userPrincipal = authentication.getPrincipal(); // 获取用户身份信息
//        Object credentials = authentication.getCredentials(); // 获取用户登录凭证
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities(); // 用户权限信息

        Map<Object, Object> jsonObj = new HashMap<>();
        jsonObj.put("code", 200);
        jsonObj.put("msg", "登录成功");
        jsonObj.put("data", JSON.toJSONString(userPrincipal));

        // 将json对象转换成字符串
        String result = JSON.toJSONString(jsonObj);
        //返回json对象到前端
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(result);
    }
}
