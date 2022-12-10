package com.example.demo.service.impl.user.msg;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.impl.utils.UserDetailsImpl;
import com.example.demo.service.user.msg.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @项目名: democloud
 * @文件名: UpdateUserServiceImpl
 * @作者: LuoTianYu
 * @日期:2022/12/9 23:20
 */
@Service
public class UpdateUserServiceImpl implements UpdateUserService {

    @Autowired
    UserMapper userMapper;
    @Override
    public Map<String, String> updateUser(Map<String, String> data) {
        // 把当前用户从本地存储中的Token取出来 对应当前的用户
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        Map<String ,String > map = new HashMap<>();
        String name = data.get("username");
        // 查询用户名是否重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",name);
        List<User> list = userMapper.selectList(queryWrapper);
        if(!list.isEmpty()){
            map.put("msg","用户名已存在");
            return map;
        }
        String sex = data.get("sex");
        if(!sex.equals("男") && !sex.equals("女")){
            map.put("msg","性别只能为男或女.");
            return map;
        }
        String content = data.get("content"); // 自我介绍
        String  school = data.get("school");

        User user1 = new User(
                user.getId(),
                name,
                user.getPassword(),
                user.getPhoto(),
                user.getRating(),
                school,
                user.getEmail(),
                content,
                sex

        );
        userMapper.updateById(user1); // 更新
        map.put("msg","success");
        return map;
    }
}
