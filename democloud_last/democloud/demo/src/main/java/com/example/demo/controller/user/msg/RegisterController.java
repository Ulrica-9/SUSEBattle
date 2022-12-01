package com.example.demo.controller.user.msg;

import com.example.demo.service.impl.user.msg.RegisterServiceImpl;
import com.example.demo.service.user.msg.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @项目名: demo
 * @文件名: RegisterController
 * @作者: LuoTianYu
 * @日期:2022/11/7 14:53
 */
@RestController
public class RegisterController {
    @Autowired
    private RegisterService registerService; // 刚刚实现的接口放进来

    @PostMapping("/user/msg/register/")
    public Map<String,String> getRegister(@RequestParam Map<String,String> map){
     //    用户信息  - 从浏览器传过来的数据
        String username = map.get("username");
        String password = map.get("password");
        String confirmedPassword = map.get("pwd2");
        String pic = map.get("pic");
        String school = map.get("school");
        String email = map.get("email");
        String personal = "";
        String sex = "";
        return registerService.getRegister(username, password, confirmedPassword,pic,school,email,personal,sex);
    }
}
