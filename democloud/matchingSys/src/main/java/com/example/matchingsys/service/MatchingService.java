package com.example.matchingsys.service;

/**
 * @项目名: democloud
 * @文件名: MatchingService
 * @作者: LuoTianYu
 * @日期:2022/11/26 21:35
 */
public interface MatchingService {
//    匹配池中添加一个玩家
    String add_one_player(Integer userID,Integer rating);
    String remove_one_player(Integer userID);
}
