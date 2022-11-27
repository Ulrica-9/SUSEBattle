package com.example.demo.service.user.msg;

import java.util.Map;

/**
 * @项目名: demo
 * @文件名: LoginService
 * @作者: LuoTianYu
 * @日期:2022/11/6 17:43
 */

// 登录
public interface LoginService {
   // 最后的登录信息反馈
   public Map<String, String> getToken(String username, String password);
}
