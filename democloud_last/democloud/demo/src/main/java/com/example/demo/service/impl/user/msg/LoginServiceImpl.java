package com.example.demo.service.impl.user.msg;

import com.example.demo.pojo.User;
import com.example.demo.service.impl.utils.UserDetailsImpl;
import com.example.demo.service.user.msg.LoginService;
import com.example.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @项目名: demo
 * @文件名: LoginServiceImpl
 * @作者: LuoTianYu
 * @日期:2022/11/6 17:53
 */

// 实现接口书写 service注解 添加到Spring当中去
@Service
public class LoginServiceImpl implements LoginService {

    //验证用户是否登录的API 注解
    @Autowired
    private AuthenticationManager authenticationManager;


    // 实现
    @Override
    public Map<String, String> getToken(String username, String password) {

        UsernamePasswordAuthenticationToken userInfo =
                new UsernamePasswordAuthenticationToken(username,password);

        // 就行判断 登录失败  自动处理
        Authentication authenticate = authenticationManager.authenticate(userInfo);

        // 取出用户
        UserDetailsImpl loginUser = (UserDetailsImpl)authenticate.getPrincipal();
        User user = loginUser.getUser();

        // 采用 jwt验证 (这里如果验证错误 会自动抛异常)
        String jwt = JwtUtil.createJWT(user.getId().toString());

        // 验证成功的返回值
        Map<String, String> map = new HashMap<>();
        map.put("error_message", "success");
        map.put("token", jwt);


        return map;
    }
}
