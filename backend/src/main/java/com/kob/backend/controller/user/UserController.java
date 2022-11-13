package com.kob.backend.controller.user;


import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
//    实现数据库的查询操作
    @Autowired
    UserMapper userMapper;
    @GetMapping("/user/all")
    public List<User> getAll(){
//         写 null 表示查询所有的
        return userMapper.selectList(null);
    }

}
