package com.example.demo.service.impl.user.msg;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.impl.user.sendMsg.SendMsgServiceImpl;
import com.example.demo.service.user.msg.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @项目名: demo
 * @文件名: RegistServiceImpl
 * @作者: LuoTianYu
 * @日期:2022/11/7 14:50
 */

@Service
public class RegisterServiceImpl implements RegisterService {
    // 这里要做数据库的查询 就把他拿出来

    @Autowired
    private UserMapper userMapper;

    // 引用密码加密
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Map<String, String> getRegister(String name, String password, String pwd2,String pic,String school,String email,String personal,String sex) {

        Map<String,String> map = new HashMap<>();
        // 打印一下刚才的Code
        //System.out.println(SendMsgServiceImpl.Code);
        // 判断用户是否为空
        if(name == null){
            map.put("error_msg","用户名不能为空");
            return map;
        }
        // 判断用户名字长度
        name = name.trim(); // delete fir&end space
        if(name.length() == 0){
            map.put("error_msg","用户名不能为空");
            return map;
        }

        if(name.length() > 20){
            map.put("error_msg","用户名长度不能超过20");
            return map;
        }

        // 密码是否非空 是否 过长
        if (password == null || pwd2 == null) {
            map.put("error_msg","密码不能为空");
            return map;
        }

        if(!pwd2.equals(password)){
            map.put("error_msg","两次密码不一致");
            return map;
        }

        if(password.length() > 100 || pwd2.length() > 100){
           map.put("error_msg","密码长度不能大于100");
           return map;
       }

        // 这里 password is error
        // pwd = pwd.trim(); // pwd fir&end space delete
       // pwd2 = pwd2.trim();
        if(password.length() ==0 || pwd2.length() ==0){
            map.put("error_msg","密码为空");
            return map;
        }
        // 查询用户名是否重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",name);
        List<User> list = userMapper.selectList(queryWrapper);
        if(!list.isEmpty()){
            map.put("error_msg","用户名已存在");
            return map;
        }
        // 进行密码加密之后存储
        String encodePassword = passwordEncoder.encode(password);
        // 图片资源 (现阶段随机放一张图片)
        String photo = "";
        if(pic == null || pic.length() == 0){
            // 默认头像
            photo = "https://cdn.staticaly.com/gh/Ulrica-9/images@master/img_Picgo/20221106173004.png";
        }else{
        //    否则就用我们自己图片
            photo = pic;
        }

        // 通过传递判断数据是否
        if(email == null || email.length() == 0){
            map.put("error_msg","邮箱不能为空");
            return map;
        }
        // System.out.println(from + "to" + email);
        // 查询邮箱是否重复
        QueryWrapper<User> queryWrapper_email = new QueryWrapper<>();
        queryWrapper_email.eq("email",email);
        List<User> list_email = userMapper.selectList(queryWrapper_email);
        if(!list_email.isEmpty()){
            map.put("error_msg","邮箱已存在");
            return map;
        }

        String user_email = email;


        // 学校
        if(school == null || school.length() ==0){
            map.put("error_msg","学校不能为空");
            return map;
        }
        // 性别
        sex = "男";
        // 个人介绍
        personal = "该用户很懒，什么都没写.";
        // 创建 (id 自增 直接设置为 null)
        User user = new User(null,name,encodePassword,photo,1500,school,user_email,personal,sex);
        userMapper.insert(user); // 放到数据库中
        map.put("error_msg","success"); // 成功

        // 成功的时候可以调用一个邮箱 来进行与用户对话 - 告诉用户 已经成功
        return map;
    }

}

