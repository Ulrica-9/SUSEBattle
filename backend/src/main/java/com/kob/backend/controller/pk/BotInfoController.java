package com.kob.backend.controller.pk;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Author 作者名
 * @Date 2022/10/28 0:33
 */
// 前后端分离
    /*
    *  @RestController - 返回的是数据
    *  @Controller - 返回的是网页
    *
    * */
@RestController
@RequestMapping("/pk/")
public class BotInfoController {
    @RequestMapping("getbotinfo/")
   public Map<String,String> getBotInfo(){
        Map<String,String> map = new HashMap<>();
        map.put("name","xinxin");
        map.put("rating","1500");
        return map;
    }
}
