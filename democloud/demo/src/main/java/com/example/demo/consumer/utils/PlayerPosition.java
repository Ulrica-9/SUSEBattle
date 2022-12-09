package com.example.demo.consumer.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

/**
 * @项目名: demo
 * @文件名: PlayerPosition
 * @作者: LuoTianYu
 * @日期:2022/11/25 9:04
 */

// 用来同步玩家的位置
@Data
@AllArgsConstructor // 有参
@NoArgsConstructor
public class PlayerPosition {
    private Integer id; // 当前用户id
    private Integer BotId; // -1 自己  / 代码
    private String BotCode;
    // 位置
    private Integer sx;
    private Integer sy;
    // 用户每一步的指令
    private List<Integer> steps;


    // 合法性检测: 检测当前蛇的身体是否在增加
    private boolean check_tail_increasing(int step){
        // 检测当前回合蛇长度
        if(step <=10 ){
            return true;
        }
        // if(step%3 ==1) return true;
        // return false;
        return step%3 == 1;
    }
    //    这里是返回蛇的身体
    public List<Snake_Cell> getSnake_Cells(){
        List<Snake_Cell> res = new LinkedList<>();

        int dx[] = {-1,0,1,0};
        int dy[] = {0,1,0,-1};

        int x = sx; // 位置
        int y = sy;
        res.add(new Snake_Cell(x,y));

        int step = 0;
        for(int d:steps){
            x+= dx[d];
            y+= dy[d];
            res.add(new Snake_Cell(x,y));
            step++;
            if(!check_tail_increasing(step)){
                res.remove(0);
            }
        }
        return res;
    }


    public String getStepsString(){
        StringBuilder res = new StringBuilder();
        for(int d : steps){
            res.append(d);
        }

        return res.toString();
    }
}
