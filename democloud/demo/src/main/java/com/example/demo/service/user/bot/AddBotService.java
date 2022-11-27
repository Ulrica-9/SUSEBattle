package com.example.demo.service.user.bot;

import java.util.Map;

/**
 * @项目名: demo
 * @文件名: AddBotService
 * @作者: LuoTianYu
 * @日期:2022/11/12 15:41
 */

// 插入一个bot
public interface AddBotService {
//    接口省略默认是public
    public Map<String,String> add(Map<String,String> data);
}
