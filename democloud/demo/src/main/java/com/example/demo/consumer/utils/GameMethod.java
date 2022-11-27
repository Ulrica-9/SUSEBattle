package com.example.demo.consumer.utils;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.consumer.WebSocketServer;
import com.example.demo.pojo.Record;
import lombok.Data;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @项目名: demo
 * @文件名: GameMethod
 * @作者: LuoTianYu
 * @日期:2022/11/25 1:03
 */

// 继承 - Thread 形成多线程
public class GameMethod extends Thread{

   private  final Integer rows; // 行数
   private final Integer cols; //列数
   private final Integer inner_walls_count; // 墙内障碍物
   private  final int[][] g;//地图

    // 上下左右方向移动
    private final static int[] dx = {-1, 0, 1, 0};
    private final static int[] dy = {0, 1, 0, -1};

    // 存储每一个玩家的所在位置
    private final  PlayerPosition pA;
    private final  PlayerPosition pB;

    // 两个玩家下一步操作 null 表示没有获取到下一步操作
    private Integer nextStepA = null;
    private Integer nextStepB = null;
    // 加锁
    private ReentrantLock reentrantLock = new ReentrantLock();

    // 游戏状态 playing 正在进行中 finished 结束
    private String status = "playing";


    // 判断谁输了
    // all - 平局 A - pA输 B - pB输
    private String loser = "";




    public GameMethod(Integer rows, Integer cols, Integer inner_walls_count,Integer pA_ID,Integer pB_ID) {
        this.rows = rows;
        this.cols = cols;
        this.inner_walls_count = inner_walls_count;
        this.g = new int[rows][cols];

    //     初始化两个玩家
        pA = new PlayerPosition(pA_ID,rows-2,1,new ArrayList<>());
        pB = new PlayerPosition(pB_ID,1,cols-2,new ArrayList<>());
    }

    public PlayerPosition getpA(){
        return pA;
    }
    public PlayerPosition getpB(){
        return pB;
    }
    // 得到两个用户的
    public void setNextStepA(Integer nextStepA){
        reentrantLock.lock(); // 加锁
        // 避免死锁
        try {
            this.nextStepA = nextStepA;
        }finally {
        //    执行之后就算报错也锁上
            reentrantLock.unlock(); // 锁上
        }

    }
    public void setNextStepB(Integer nextStepB){
        reentrantLock.lock();
        try {
            this.nextStepB = nextStepB;
        }finally {
            reentrantLock.unlock();
        }
    }


    //    返回地图
    public int[][] getMap(){
        return g;
    }


//    地图绘制
    private boolean drawMap(){
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                g[i][j] = 0;
            }
        }
        // 加强
        for (int r = 0; r < this.rows; r ++ ) {
            g[r][0] = g[r][this.cols - 1] = 1;
        }
        for (int c = 0; c < this.cols; c ++ ) {
            g[0][c] = g[this.rows - 1][c] = 1;
        }

        // 随机
        Random random = new Random();
        for (int i = 0; i < this.inner_walls_count / 2; i ++ ) {
            for (int j = 0; j < 1000; j ++ ) {
                int r = random.nextInt(this.rows);
                int c = random.nextInt(this.cols);

                if (g[r][c] == 1 || g[this.rows - 1 - r][this.cols - 1 - c] == 1)
                    continue;
                if (r == this.rows - 2 && c == 1 || r == 1 && c == this.cols - 2)
                    continue;

                g[r][c] = g[this.rows - 1 - r][this.cols - 1 - c] = 1;
                break;
            }
        }

        return  isCheck(this.rows - 2, 1, 1, this.cols - 2); // 交给函数进行判断
    }

    // 判断是否连通
    private boolean isCheck(int sx, int sy, int tx, int ty) {
        if (sx == tx && sy == ty) return true;
        // 初始化
        g[sx][sy] = 1;
        for (int i = 0; i < 4; i ++ ) {
            int x = sx + dx[i], y = sy + dy[i];
            if (x >= 0 && x < this.rows && y >= 0 && y < this.cols && g[x][y] == 0) {
                if (isCheck(x, y, tx, ty)) {
                    g[sx][sy] = 0; // 恢复现场
                    return true;
                }
            }
        }
        g[sx][sy] = 0;
        return false;
    }


    // 随机地图
    public void createMap(){
        for (int i = 0; i < 1000; i++) {
            if(drawMap()){
            //    如果绘制成功
                break;
            }
        }
    }



    // change 合法性
    private boolean check_valid(List<Snake_Cell> snake1,List<Snake_Cell> snake2){
        int n = snake1.size();
        Snake_Cell cell = snake1.get(n - 1);

        // 撞墙
        if(g[cell.x][cell.y] == 1){
            return false;
        }

        for (int i = 0; i < n-1; i++) {
            if(snake1.get(i).x == cell.x && snake1.get(i).y == cell.y){
                return false; // 重叠
            }
        }

        for (int i = 0; i < n-1; i++) {
            if(snake2.get(i).x == cell.x && snake2.get(i).y == cell.y){
                return false; // 重叠
            }
        }

        // 没找到矛盾点就返回为真
        return true;
    }

    // 判断两名玩家下一操作是否合法
    private void judge(){
    //    这里是先把蛇给取出来
        List<Snake_Cell> cellsA = pA.getSnake_Cells();
        List<Snake_Cell> cellsB = pB.getSnake_Cells();


        boolean validA = check_valid(cellsA,cellsB);
        boolean validB = check_valid(cellsB,cellsA);

        if(!validA || !validB)
        {
        //    游戏结束
            status = "finished";
            if(!validA && !validB){
            //    全部都输了
                loser = "all";
            }else if(!validA){
            //    A输了
                loser = "A";
            }else{
            //    B输了
                loser = "B";
            }
        }
    }



    // 广播信息
    private void sendAllMessage(String message){

        // 防止用户 alt +f4 退出 报异常
        if(WebSocketServer.users.get(pA.getId()) != null) {
            WebSocketServer.users.get(pA.getId()).sendMessage(message);
        }
        if(WebSocketServer.users.get(pB.getId()) != null) {
            WebSocketServer.users.get(pB.getId()).sendMessage(message);
        }
    }

    // 向两个玩家传递移动信息
    private void sendMove(){
        reentrantLock.lock();
        try {
            JSONObject resp = new JSONObject();
            resp.put("event", "move");
            resp.put("a_direction", nextStepA);
            resp.put("b_direction", nextStepB);
            sendAllMessage(resp.toJSONString());
            nextStepA = nextStepB = null;

        }finally {
            reentrantLock.unlock();
        }

    }
    
    // 存数据库
    private void saveToDatabase(){
        Record record = new Record(
                null,
                pA.getId(),
                pA.getSx(),
                pA.getSy(),
                pB.getId(),
                pB.getSx(),
                pB.getSy(),
                pA.getStepsString(),
                pB.getStepsString(),
                getMapString(),
                loser,
                new Date()
        );
        // 放入数据库
        WebSocketServer.recordMapper.insert(record);
    }


    // 地图转为字符串存到数据库
    public String getMapString(){
        StringBuilder res = new StringBuilder();
        for(int i=0;i<rows;i++){
            for (int j = 0; j < cols; j++) {
                res.append(g[i][j]);
            }
        }
        return res.toString();
    }



    // 向两个玩家返回结果
    private void sendResult(){
        JSONObject resp = new JSONObject();
        resp.put("event", "result");
        resp.put("loser", loser);
        saveToDatabase(); // 存数据库
        // 往前端返回数据
        sendAllMessage(resp.toJSONString());
    }

    // 新线程的入口函数
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            if(nextStep()){
                judge();
                // 正在进行中
                if(status.equals("playing")){
                    sendMove(); // 对局进行 - 同步
                }else{
                //    如果结束 - 返回结果
                    sendResult();
                    break;
                }
            }else{
                status = "finished";
                reentrantLock.lock(); // 加锁
                try {
                    if (nextStepA == null && nextStepB == null) {
                        loser = "all";
                    } else if (nextStepA == null) {
                        loser = "A";
                    } else {
                        loser = "B";
                    }
                }finally {
                    reentrantLock.unlock();
                }
                sendResult();
                break;
            }
        }
    }

//     辅助函数 - 等待玩家下一步操作
    private boolean nextStep(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // sleep - 进行判断 用户看到蛇会动的延迟时间 (i 和 Thread.sleep确定)
        for (int i = 0; i < 50; i++) {
            try {
                Thread.sleep(100);
                reentrantLock.lock(); // 加锁
                try {
                    // 判断两个玩家操作
                    if(nextStepA != null && nextStepB != null){
                        pA.getSteps().add(nextStepA);
                        pB.getSteps().add(nextStepB);
                        return true;
                    }

                }finally {
                    reentrantLock.unlock(); // 解锁
                }

            }catch (InterruptedException e){
               e.printStackTrace();
            }
        }

        return false;
    }
}
