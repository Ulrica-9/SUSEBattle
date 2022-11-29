package com.example.demo.controller.pk;

import com.example.demo.service.pk.ReceiveBotMoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @项目名: democloud
 * @文件名: ReceiveBotMoveController
 * @作者: LuoTianYu
 * @日期:2022/11/28 22:00
 */
@RestController
public class ReceiveBotMoveController {

    @Autowired
    private ReceiveBotMoveService receiveBotMoveService;



    @PostMapping("/pk/receive/bot/move/")
    public String receiveBotMove(@RequestParam MultiValueMap<String,String> data){
        Integer userId = Integer.parseInt(Objects.requireNonNull(data.getFirst("user_id")));
        Integer direction = Integer.parseInt(Objects.requireNonNull(data.getFirst("direction")));

        return receiveBotMoveService.receiveBotMove(userId,direction);
    }


}
