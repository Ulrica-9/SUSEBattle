package com.example.demo.service.impl.passenger;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.user.passenger.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @项目名: democloud
 * @文件名: passengerService
 * @作者: LuoTianYu
 * @日期:2022/12/8 12:29
 */
@Service
public class PassengerServiceImpl implements PassengerService {

    // 注入数据库
    @Autowired
    private UserMapper userMapper;

    @Autowired
    // 引用数据加密算法
    private PasswordEncoder passwordEncoder;
    @Override
    public Map<String, String> getRegister() {
        Map<String,String> map = new HashMap<>();
        // 产生随机用户名 和 密码
        String name = "游客" + randomCode();
        // 以防万一 判重
        // 查询邮箱是否重复
        QueryWrapper<User> queryWrapper_name = new QueryWrapper<>();
        queryWrapper_name.eq("username",name);
        List<User> list_name = userMapper.selectList(queryWrapper_name);
        while(true){
            // 判重
            if(list_name.isEmpty()) break;
            else {
                name = "游客" + randomCode();
            }
        }
        System.out.println(name);
        String password = "123456";
        System.out.println(password);
        // 进行密码加密之后存储
        String encodePassword = passwordEncoder.encode(password);
        String photo = "https://cdn.staticaly.com/gh/Ulrica-9/images@master/img_Picgo/20221106173004.png";
        String school = "暂无";
        String email = "暂无";
        // 这两个东西暂时不要
        String personal = "该用户很懒，什么都没写.";
        String sex = "男";
        User user = new User(null,name,encodePassword,photo,1500,school,email,personal,sex);
        userMapper.insert(user);
        map.put("error_msg","success"); // 成功
        map.put("username",name);
        map.put("password",password);
        return map;
    }
    /**
     * 随机生成6位数的验证码
     * @return String code
     *
     */
    public String randomCode(){
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }
}
