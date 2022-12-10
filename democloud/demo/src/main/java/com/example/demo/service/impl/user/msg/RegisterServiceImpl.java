package com.example.demo.service.impl.user.msg;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.impl.images.LocalStoreImagesServiceImpl;
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
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.*;

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
    public Map<String, String> getRegister(String name, String password, String pwd2, MultipartFile pic, String school, String email, String personal, String sex) {

        Map<String,String> map = new HashMap<>();
        // 判断用户是否为空
        if(name == null){
            map.put("error_msg","用户名不能为空");
            return map;
        }
        // 判断用户名字长度
        name = name.trim();
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


       //  图片资源
       //  先对图片文件进行一个判断
       String photo = getPhoto(pic);

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
        school = "四川轻化工大学"; // 学校
        sex = "男";// 性别
        personal = "该用户很懒，什么都没写.";// 个人介绍
        User user = new User(null,name,encodePassword,photo,1500,school,user_email,personal,sex);// 创建 (id 自增 直接设置为 null)
        userMapper.insert(user); // 放到数据库中
        map.put("error_msg","success"); // 成功
        // 成功的时候可以调用一个邮箱 来进行与用户对话 - 告诉用户 已经成功
        return map;
    }

//     处理头像
    private String getPhoto(MultipartFile file){
        String URL = "";
        // 文件名
        String fileName = file.getOriginalFilename();
        // 获取文件后缀名
        String extension = fileName.substring(fileName.indexOf("."));
        String uploadFolder = "E://web/img/";//获取图片路径
        // 生成一个新的文件名
        fileName = UUID.randomUUID() + extension;
        File dest = new File(uploadFolder + fileName);// 整合文件
        // 检测文件目录是否存在 不存在则创建
        if (!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            URL = "http://localhost:4000/img/" + fileName; // 映射路径
            return URL;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("上传文件失败！");
            return null;
        }

    }

}

