package com.example.demo.service.impl.pk;

import com.example.demo.consumer.WebSocketServer;
import com.example.demo.service.pk.StartGameService;
import org.springframework.stereotype.Service;

/**
 * @项目名: democloud
 * @文件名: StartGameServiceImpl
 * @作者: LuoTianYu
 * @日期:2022/11/27 1:13
 */
@Service
public class StartGameServiceImpl implements StartGameService {
    @Override
    public String startGame(Integer user_xID, Integer aBotId,Integer user_yID,Integer bBotId) {
        System.out.println("start game: " + user_xID + " " + user_yID);

        WebSocketServer.startGame(user_xID,aBotId,user_yID,bBotId);
        return "start game success";
    }
}
