package com.example.demo.service.impl.pk;

import com.example.demo.consumer.WebSocketServer;
import com.example.demo.consumer.utils.GameMethod;
import com.example.demo.service.pk.ReceiveBotMoveService;
import org.springframework.stereotype.Service;

/**
 * @项目名: democloud
 * @文件名: ReceiveBotMoveServiceImpl
 * @作者: LuoTianYu
 * @日期:2022/11/28 21:52
 */

@Service
public class ReceiveBotMoveServiceImpl implements ReceiveBotMoveService {


    @Override
    public String receiveBotMove(Integer userId, Integer direction) {
        System.out.println("receive bot move" + userId + " " + direction +" ");

        if(WebSocketServer.users.get(userId) != null){
            GameMethod game = WebSocketServer.users.get(userId).game;
            if(game!=null){
                if(game.getpA().getId().equals(userId)){
                    game.setNextStepA(direction);
                }else if(game.getpB().getId().equals(userId)){
                    game.setNextStepB(direction);
                }
            }
        }

        return "receive bot move success";
    }
}
