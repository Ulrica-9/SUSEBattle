package com.example.demo.service.impl.user.sendMsg;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.user.sendMsg.SendMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @项目名: democloud
 * @文件名: SendMsgServiceImpl
 * @作者: LuoTianYu
 * @日期:2022/11/30 22:00
 */
@Service
public class SendMsgServiceImpl implements SendMsgService {

    //public static String Code;
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from_mail; // 获取到的邮箱地址

    // 注入数据库
    @Autowired
    private UserMapper userMapper;
    @Override
    public Map<String, String> getEmail(String email) {
        // 发邮件
        Map<String,String> map = new HashMap<>();

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
            map.put("error_msg","该邮箱已经被注册啦.");
            return map;
        }

        // 进行一个验证
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject("对战平台!");
            String code = randomCode(); // 随机


            map.put("code",code); // 传输一下验证码 - 根据邮箱地址进行返回
            //Code = code;
            //System.out.println(Code);
            mailMessage.setText("你本次的验证码为 " + code );
            mailMessage.setTo(email);
            mailMessage.setFrom(from_mail);
            javaMailSender.send(mailMessage);

        }catch (Exception e){
            e.printStackTrace();
        }

        map.put("error_msg","success");

        return map;

    }
    /**
     * 随机生成6位数的验证码
     * @return String code
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
