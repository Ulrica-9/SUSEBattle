package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

//    Autowired - 使用数据库
    @Autowired
    private UserMapper userMapper;



//    重写
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        根据username返回数据库中 对应的信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        查询
        queryWrapper.eq("username",username);
        User user = userMapper.selectOne(queryWrapper);
        if(user == null){
//        抛一个异常
            throw new RuntimeException("用户不存在");
        }
        return new UserDetailsImpl(user);
    }

}
