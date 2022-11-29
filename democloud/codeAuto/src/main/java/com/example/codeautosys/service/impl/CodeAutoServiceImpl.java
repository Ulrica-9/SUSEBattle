package com.example.codeautosys.service.impl;

import com.example.codeautosys.service.CodeAutoService;
import org.springframework.stereotype.Service;

/**
 * @项目名: democloud
 * @文件名: CodeAutoServiceImpl
 * @作者: LuoTianYu
 * @日期:2022/11/28 16:24
 */
@Service
public class CodeAutoServiceImpl implements CodeAutoService{

    public final static BotPool botPool = new BotPool();
    @Override
    public String addCode(Integer userId, String botCode, String input) {

        System.out.println("add code : " + userId + " " + botCode + " " + input);
        botPool.addBot(userId,botCode,input);
        return "add code success";

    }
}
