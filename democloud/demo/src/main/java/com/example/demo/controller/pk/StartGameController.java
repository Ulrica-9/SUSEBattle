package com.example.demo.controller.pk;

import com.example.demo.service.pk.StartGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @项目名: democloud
 * @文件名: StartGameController
 * @作者: LuoTianYu
 * @日期:2022/11/27 1:16
 */

@RestController
public class StartGameController {

    // 注入接口
    @Autowired
    private StartGameService startGameService;


    @PostMapping("/pk/start/game/")
    public String startGame(@RequestParam MultiValueMap<String, String> data) {
        Integer aId = Integer.parseInt(Objects.requireNonNull(data.getFirst("a_id")));
        Integer aBotId = Integer.parseInt(Objects.requireNonNull(data.getFirst("a_bot_id")));
        Integer bId = Integer.parseInt(Objects.requireNonNull(data.getFirst("b_id")));
        Integer bBotId = Integer.parseInt(Objects.requireNonNull(data.getFirst("b_bot_id")));

        return startGameService.startGame(aId, aBotId,bId,bBotId);
    }
}
