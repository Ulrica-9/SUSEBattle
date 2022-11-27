package com.example.demo.controller.user.bot;

import com.example.demo.service.user.bot.DeleteBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @项目名: demo
 * @文件名: DeleteController
 * @作者: LuoTianYu
 * @日期:2022/11/12 20:02
 */
@RestController
public class DeleteController {
    @Autowired
    private DeleteBotService deleteBotService;

    @PostMapping ("/user/del/bot/")
    public Map<String,String> del(@RequestParam Map<String,String> data){

        return deleteBotService.del(data);
    }
}
