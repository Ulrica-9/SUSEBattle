package com.example.demo.controller.user.bot;

import com.example.demo.service.user.bot.AddBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @项目名: demo
 * @文件名: AddController
 * @作者: LuoTianYu
 * @日期:2022/11/12 16:30
 */
@RestController
public class AddController {
    @Autowired
    private AddBotService addBotService;

    // 修改数据库
    @PostMapping("/user/bot/add/")
    public Map<String,String> add(@RequestParam Map<String,String> data){
        return addBotService.add(data);
    }
}
