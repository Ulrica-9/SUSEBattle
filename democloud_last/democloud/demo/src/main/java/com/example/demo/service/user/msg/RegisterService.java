package com.example.demo.service.user.msg;

import java.util.Map;

/**
 * @项目名: demo
 * @文件名: zhuceService
 * @作者: LuoTianYu
 * @日期:2022/11/6 17:43
 */

// 注册
public interface RegisterService {
    // 用户注册信息 - 需要用户输入两次密码 pwd pwd2
    public Map<String,String> getRegister(String name,String pwd,String pwd2,String pic,String school,String email,String personal,String sex);
}
