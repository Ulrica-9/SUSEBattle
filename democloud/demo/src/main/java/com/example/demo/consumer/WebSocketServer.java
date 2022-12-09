package com.example.demo.consumer;

/**
 * @项目名: demo
 * @文件名: WebSocketServer
 * @作者: LuoTianYu
 * @日期:2022/11/24 17:18
 */
import com.alibaba.fastjson.JSONObject;
import com.example.demo.consumer.utils.GameMethod;
import com.example.demo.consumer.utils.JwtAuthentication;
import com.example.demo.mapper.BotMapper;
import com.example.demo.mapper.RecordMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.BotInfo;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

// 不要以'/'结尾 - 对应链接
@Component
@ServerEndpoint("/websocket/{token}")
public class WebSocketServer {
    // 加 static 每个用户都可以得到的信息 - 全局可见
    final public static ConcurrentHashMap<Integer, WebSocketServer> users = new ConcurrentHashMap<>();
    private User user; // 哪个用户
    private Session session = null; // 与传统session不同
    //注入数据库 (与其他有区分)
    public static UserMapper userMapper;
    public static RecordMapper recordMapper;
    public  static RestTemplate restTemplate;
    public GameMethod game = null;

    private static BotMapper botMapper;


    // 两个URL
    private final static String addPlayerURL = "http://127.0.0.1:4001/player/add/";
    private final static String removePlayerURL = "http://127.0.0.1:4001/player/remove/";



    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        WebSocketServer.userMapper = userMapper;
    }


    @Autowired
    public void setRecordMapper(RecordMapper recordMapper){WebSocketServer.recordMapper = recordMapper;}


    @Autowired
    public void setRestTemplate(RestTemplate restTemplate){WebSocketServer.restTemplate = restTemplate;}

    @Autowired
    public void setBotMapper(BotMapper botMapper){WebSocketServer.botMapper = botMapper;}

    @OnOpen
    // 建立连接 - 每次都建立一个客户端进行匹配 - 从前端发送请求过来
    public void onOpen(Session session, @PathParam("token") String token) throws IOException {
    //    存一下session
        this.session = session;
        System.out.println("Connected");
        Integer userId = JwtAuthentication.getUserId(token);
        this.user = userMapper.selectById(userId);


        if(this.user!=null){
        //    用户存在
            users.put(userId,this);
        }else{
            this.session.close(); // 否则断开
        }
        System.out.println(users);
    }

    @OnClose
    // 关闭链接的时候自动触发
    public void onClose() {
        System.out.println("DisConnected");
    //    断开链接的时候删
        if (this.user != null) {

            // 维护数组
            users.remove(this.user.getId());
        }
    }

    public static void startGame(Integer aId, Integer aBotId,Integer bId,Integer bBotId) {
        User user_x = userMapper.selectById(aId);
        User user_y = userMapper.selectById(bId);

        BotInfo bot_x = botMapper.selectById(aBotId);
        BotInfo bot_y = botMapper.selectById(bBotId);




        // 处理地图 - 先处理默认
        GameMethod game = new GameMethod(13,
                14,
                20,
                user_x.getId(),
                bot_x,
                user_y.getId(),
                bot_y
        );
        game.createMap();

        // 多线程
        if(users.get(user_x.getId())!=null){
            users.get(user_x.getId()).game = game;
        }
        if(users.get(user_y.getId())!=null){
            users.get(user_y.getId()).game = game;
        }

        game.start();

        JSONObject respGame = new JSONObject();
        respGame.put("a_id",game.getpA().getId());
        respGame.put("a_sx",game.getpA().getSx());
        respGame.put("a_sy",game.getpA().getSy());
        respGame.put("b_id",game.getpB().getId());
        respGame.put("b_sx",game.getpB().getSx());
        respGame.put("b_sy",game.getpB().getSy());
        // 地图更新信息
        respGame.put("map",game.getMap());

        JSONObject respA = new JSONObject();
        respA.put("event","start-matching");
        respA.put("opponent_username",user_y.getUsername());
        respA.put("opponent_photo",user_y.getPhoto());
        respA.put("game",respGame);
        if(users.get(user_x.getId())!=null) {
            users.get(user_x.getId()).sendMessage(respA.toJSONString()); // 获取连接并向前端发送信息
        }
        JSONObject respB = new JSONObject();
        respB.put("event","start-matching");
        respB.put("opponent_username",user_x.getUsername());
        respB.put("opponent_photo",user_x.getPhoto());
        respB.put("game",respGame);
        if(users.get(user_y.getId())!=null) {
            users.get(user_y.getId()).sendMessage(respB.toJSONString()); // 获取连接并向前端发送信息
        }
    }
    // 开始匹配
    private void startMatching(Integer botId){
        System.out.println("start Matching");
    //    向后端发送请求
        MultiValueMap<String,String> data = new LinkedMultiValueMap<>();
        data.add("bot_id",botId.toString());
        data.add("user_id",this.user.getId().toString());
        data.add("rating",this.user.getRating().toString());
        restTemplate.postForObject(addPlayerURL,data,String.class);
    }
    // 取消匹配
    private void stopMatching(){
        System.out.println("stop Matching");
        //    向后端发送请求
        MultiValueMap<String,String> data = new LinkedMultiValueMap<>();
        data.add("user_id",this.user.getId().toString());
        restTemplate.postForObject(removePlayerURL,data,String.class);
    }


    private void move(int direction){
// 判断当前玩家
        if(game.getpA().getId().equals(user.getId())){
            // 自己上才用
            if(game.getpA().getBotId().equals(-1)) {
                game.setNextStepA(direction);
            }
        }else if(game.getpB().getId().equals(user.getId())){
            if(game.getpB().getBotId().equals(-1)) {
                game.setNextStepB(direction);
            }
        }


    }
    @OnMessage
    // 从客户端接收消息的时候
    public void onMessage(String message, Session session) {
        // 当做路由
        System.out.println("receive message!");
        JSONObject data = JSONObject.parseObject(message);
        String event = data.getString("event");
        // 前端传到后端
        if ("start-matching".equals(event)) {
            startMatching(data.getInteger("bot_id"));
        } else if ("stop-matching".equals(event)) {
            stopMatching();
        } else if ("move".equals(event)) {
            move(data.getInteger("direction"));
        }

    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();

    }

    // 后端向前端发送信息
    public void sendMessage(String message) {
        synchronized (this.session) {
            try {
                this.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

