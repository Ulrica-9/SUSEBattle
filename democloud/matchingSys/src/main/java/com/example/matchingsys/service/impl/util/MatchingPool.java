package com.example.matchingsys.service.impl.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @项目名: democloud
 * @文件名: MatchingPool
 * @作者: LuoTianYu
 * @日期:2022/11/27 0:19
 */
@Component
public class MatchingPool extends Thread {
    // All users
    // 多线程共用
    private static List<Player> players = new ArrayList<>();
    private final ReentrantLock  reentrantLock = new ReentrantLock();
    private static RestTemplate restTemplate;


    private final static String startGameURL = "http://127.0.0.1:4000/pk/start/game/";

    // 注入
    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        MatchingPool.restTemplate = restTemplate;
    }

    // 添加player
    public  void add_Player(Integer userID,Integer rating){
        reentrantLock.lock(); // 加锁
        // 防止异常 -> 死锁
        try {
            players.add(new Player(userID,rating,0));
        }finally {
            reentrantLock.unlock();
        }
    }
    // 删除player
    public void remove_Player(Integer userID){
        reentrantLock.lock();
        try {
            List<Player> newPlayers = new ArrayList<>();
            for (Player player: players) {
                if (!player.getUserId().equals(userID)){
                    newPlayers.add(player);
                }
            }
            players = newPlayers;
        }finally {
            reentrantLock.unlock();
        }
    }


    // 所有玩家等待时间 +1
    private void appendWaitingTime(){
        for(Player player:
                players) {
            player.setWaitingTime(player.getWaitingTime() + 1);
        }
    }





    // 判断两个玩家是否匹配
    private boolean checkMatch(Player a,Player b){
        int ratingDelta = Math.abs(a.getRating() - b.getRating());
        // int waitingTime = Math.max(user_x.getWaitingTime(),user_y.getWaitingTime()); - 两个中任意一个都可以
        int waitingTime = Math.min(a.getWaitingTime(), b.getWaitingTime());

        return ratingDelta <= waitingTime * 10;
    }

    // 返回a b 的匹配 结果
    private void sendResult(Player a,Player b){
        System.out.println("send result" + a + " " + b);
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("a_id", a.getUserId().toString());
        data.add("b_id", b.getUserId().toString());
        restTemplate.postForObject(startGameURL, data, String.class);
    }


    // 尝试匹配所有玩家
    private void matchAllPlayers(){
        System.out.println("match players: " + players.toString());

        boolean[] used = new boolean[players.size()];
        for (int i = 0; i < players.size(); i ++ ) {
            if (used[i]) continue;
            for (int j = i + 1; j < players.size(); j ++ ) {
                if (used[j]) continue;
                Player a = players.get(i), b = players.get(j);
                if (checkMatch(a, b)) {
                    used[i] = used[j] = true;
                    sendResult(a, b);
                    break;
                }
            }
        }

        //     删除已经用过的player
        List<Player> newPlayers = new ArrayList<>();
        for (int i = 0; i < players.size(); i ++ ) {
            if (!used[i]) {
                newPlayers.add(players.get(i));
            }
        }
        players = newPlayers;
    }



    @Override
    public void run() {
        // 多线程 - 启动接口 run() - 一直死循环匹配
        while (true){
            try {
                Thread.sleep(1000);
                reentrantLock.lock();
                try {
                    // 产生读写 - 加锁
                    appendWaitingTime();// 每隔一秒 等待时间 + 1
                    matchAllPlayers();
                }finally {
                    reentrantLock.unlock();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
                break;
            }
        }
    }
}
