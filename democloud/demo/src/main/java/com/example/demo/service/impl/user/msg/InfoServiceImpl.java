package com.example.demo.service.impl.user.msg;

import com.example.demo.pojo.User;
import com.example.demo.service.impl.utils.UserDetailsImpl;
import com.example.demo.service.user.msg.InfoService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @项目名: demo
 * @文件名: InfoServiceImpl
 * @作者: LuoTianYu
 * @日期:2022/11/7 0:58
 */
// 授权成功后
@Service
public class InfoServiceImpl implements InfoService {
    @Override
    public Map<String, String> getinfo() {
        // 可以找到我们的user了
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        // 找到User
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        Map<String, String> map = new HashMap<>();
        map.put("error_message","success");
        map.put("id",user.getId().toString());
        map.put("username",user.getUsername());
        map.put("photo",user.getPhoto());
        map.put("email",user.getEmail());
        map.put("sex",user.getSex());
        map.put("personal",user.getPersonal());
        map.put("rating",user.getRating().toString()); //天梯分
        map.put("school",user.getSchool());
        return map;
    }

}
