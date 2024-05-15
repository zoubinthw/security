package com.atguigu.securitydemo.config;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // 当应用程序试图访问一个需要用户登录的界面, 此时系统会将应用程序自动跳转到登录页面, 自动跳转的行为在这里定义为一个json结果
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        String message = authException.getLocalizedMessage();

        String message = "请重新登录"; //也可以自定义
        Map<Object, Object> jsonObj = new HashMap<>();
        jsonObj.put("code", -1);
        jsonObj.put("msg", message);

        // 将json对象转换成字符串
        String result = JSON.toJSONString(jsonObj);
        //返回json对象到前端
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(result);
    }
}
