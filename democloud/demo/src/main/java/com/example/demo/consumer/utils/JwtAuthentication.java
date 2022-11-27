package com.example.demo.consumer.utils;

import com.example.demo.utils.JwtUtil;
import io.jsonwebtoken.Claims;

/**
 * @项目名: demo
 * @文件名: JwtAuthentication
 * @作者: LuoTianYu
 * @日期:2022/11/24 21:47
 */
public class JwtAuthentication {
    public static Integer getUserId(String token){
        // 解析用户ID 能够解析 合法 否则不合法
        Integer userId = -1; // -1 不存在 否则存在
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = Integer.parseInt(claims.getSubject());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return userId;
    }
}
