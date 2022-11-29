package com.example.demo.service.user.bot;

import com.example.demo.pojo.BotInfo;

import java.util.List;

/**
 * @项目名: demo
 * @文件名: GetBotService
 * @作者: LuoTianYu
 * @日期:2022/11/12 15:43
 */
public interface GetBotService {
//    返回自己所有bot -> 通过token  所以不同传参
    List<BotInfo> getList();
}
