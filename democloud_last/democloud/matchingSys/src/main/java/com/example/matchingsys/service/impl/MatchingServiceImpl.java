package com.example.matchingsys.service.impl;


import com.example.matchingsys.service.MatchingService;
import com.example.matchingsys.service.impl.util.MatchingPool;
import org.springframework.stereotype.Service;

/**
 * @项目名: democloud
 * @文件名: MatchingServiceImpl
 * @作者: LuoTianYu
 * @日期:2022/11/26 21:37
 */

@Service
public class MatchingServiceImpl implements MatchingService {
    public final static MatchingPool matchingPool = new MatchingPool();
    @Override
    public String add_one_player(Integer userID, Integer rating,Integer botId) {
        System.out.println("add  player: " + userID+  " " + rating);
        // 匹配池 + user
        matchingPool.add_Player(userID,rating,botId);
        return "add player success";
    }

    // 重写
    @Override
    public String remove_one_player(Integer userID) {
        System.out.println("remove  player: " + userID);
        // 匹配池 - user
        matchingPool.remove_Player(userID);
        return "remove a player success";
    }
}
