package com.example.codeautosys.service.impl;

import com.example.codeautosys.utils.BotInterface;
import org.joor.Reflect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * @项目名: democloud
 * @文件名: Consumer
 * @作者: LuoTianYu
 * @日期:2022/11/28 21:10
 */
@Component
public class Consumer extends Thread{

    private static RestTemplate restTemplate;

    private Bot bot;

    private final static String receiveBotMoveURL = "http://127.0.0.1:4000/pk/receive/bot/move/";



    @Autowired
    public void setRestTemplate(RestTemplate restTemplate){
        Consumer.restTemplate =restTemplate;
    }



    public void startTimeout(long timeout,Bot bot){
        this.bot = bot;
        this.start(); // 开新线程

        try {
            this.join(timeout); // 持续到了timeout s 没有结束就中断
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            this.interrupt(); // 防止死循环 - 结束
        }

    }



    private String addUid(String code,String uid){
    //    code 中 content 逻辑不一样
        int idx = code.indexOf(" implements com.example.codeautosys.utils.BotInterface");
    //    找到后 在之前+ 一个随机字符串


        return code.substring(0,idx) + uid + code.substring(idx);
    }
    @Override
    public void run() {

        // 随机字符串
        UUID uuid = UUID.randomUUID(); // 每次返回结果不一样
        String uid = uuid.toString().substring(0,8);
        BotInterface botInterface = Reflect.compile(
                "com.example.codeautosys.utils.Bot" + uid,
                       addUid(bot.getBotCode(),uid)
        ).create().get();

        Integer direction = botInterface.nextMove(bot.getInput());

        System.out.println("move-direction : " + bot.getUserId() + " " + direction);

        MultiValueMap<String,String> date = new LinkedMultiValueMap<>();


        date.add("user_id",bot.getUserId().toString());


        date.add("direction",direction.toString());


        restTemplate.postForObject(receiveBotMoveURL,date,String.class);
    }
}
