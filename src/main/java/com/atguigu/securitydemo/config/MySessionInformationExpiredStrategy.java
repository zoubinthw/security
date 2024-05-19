package com.atguigu.securitydemo.config;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MySessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        // 创建结果对象
        Map<Object, Object> jsonObj = new HashMap<>();
        jsonObj.put("code", -1);
        jsonObj.put("msg", "该账号已从其他帐号登录");

        // 将json对象转换成字符串
        String result = JSON.toJSONString(jsonObj);
        //返回json对象到前端
        HttpServletResponse response = event.getResponse();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(jsonObj);
    }
}
