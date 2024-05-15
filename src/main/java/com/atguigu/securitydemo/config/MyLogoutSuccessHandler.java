package com.atguigu.securitydemo.config;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Map<Object, Object> jsonObj = new HashMap<>();
        jsonObj.put("code", 200);
        jsonObj.put("msg", "注销成功");

        // 将json对象转换成字符串
        String result = JSON.toJSONString(jsonObj);
        //返回json对象到前端
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(result);
    }
}
