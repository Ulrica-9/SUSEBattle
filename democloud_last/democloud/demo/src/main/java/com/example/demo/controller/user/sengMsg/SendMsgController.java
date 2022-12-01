package com.example.demo.controller.user.sengMsg;

import com.example.demo.service.user.sendMsg.SendMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @项目名: democloud
 * @文件名: SendMsgController
 * @作者: LuoTianYu
 * @日期:2022/11/30 22:01
 */

// Controller
@RestController
public class SendMsgController {

    @Autowired
    private SendMsgService sendMsgService;


    @PostMapping("/user/email/")
    public Map<String,String> getEmailMsg(@RequestParam Map<String,String> map){
        // 接收前端传过来的数据并传回后端

        System.out.println("测试");
        String email = map.get("email");
        return sendMsgService.getEmail(email);
    }
}
