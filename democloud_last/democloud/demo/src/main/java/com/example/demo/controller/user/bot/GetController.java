package com.example.demo.controller.user.bot;

import com.example.demo.pojo.BotInfo;
import com.example.demo.service.user.bot.GetBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @项目名: demo
 * @文件名: GetController
 * @作者: LuoTianYu
 * @日期:2022/11/12 21:14
 */
@RestController
public class GetController {

    @Autowired
    private GetBotService getBotService;


    @GetMapping("/user/get/bot/")
    public List<BotInfo> getList(){
        return getBotService.getList();
    }

}
