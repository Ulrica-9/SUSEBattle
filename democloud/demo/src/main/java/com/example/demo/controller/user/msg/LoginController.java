package com.example.demo.controller.user.msg;


import com.example.demo.service.user.msg.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
/**
 * @项目名: demo
 * @文件名: LoginController
 * @作者: LuoTianYu
 * @日期:2022/11/6 21:50
 */
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService; // 刚刚实现的接口放进来

    // 这里采用Post mapping 因为 Post看不见明文
    @PostMapping("/user/msg/token/") // 可自定义路径 但是要到SecurityConfig中去放行
    public Map<String,String> getLogin(@RequestParam Map<String,String> map){
        String username = map.get("username");
        String password = map.get("password");
        return loginService.getToken(username,password);
    }

}
