package com.example.demo.controller.user.bot;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.service.user.bot.UpdateBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @项目名: demo
 * @文件名: UpdateController
 * @作者: LuoTianYu
 * @日期:2022/11/12 20:19
 */
@RestController
public class UpdateController {

    @Autowired
    private UpdateBotService updateBotService;

    @PostMapping("/user/update/bot/")
    public Map<String,String> update(@RequestParam Map<String,String> data){
        return updateBotService.update(data);
    }
}
