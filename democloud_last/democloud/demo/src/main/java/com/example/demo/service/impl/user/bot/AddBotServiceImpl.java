package com.example.demo.service.impl.user.bot;

import com.example.demo.mapper.BotMapper;
import com.example.demo.pojo.BotInfo;
import com.example.demo.pojo.User;
import com.example.demo.service.impl.utils.UserDetailsImpl;
import com.example.demo.service.user.bot.AddBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @项目名: demo
 * @文件名: AddBotServiceImpl
 * @作者: LuoTianYu
 * @日期:2022/11/12 15:53
 */
@Service
public class AddBotServiceImpl implements AddBotService {
    // 数据库接口注入
    @Autowired
    private BotMapper botMapper;

    @Override
    public Map<String, String> add(Map<String, String> data) {
        // 把当前用户从本地存储中的Token取出来 对应当前的用户
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();


        // 添加时 三个必要的内容
        String bot_name = data.get("name");
        String bot_description = data.get("description");
        String bot_content = data.get("content");

        Map<String,String> map = new HashMap<>();
        if(bot_name == null || bot_name.length() ==0 ){
            map.put("error_msg","标题不能为空");
            return map;
        }

        if(bot_name.length() > 100 ){
            map.put("error_msg","标题长度不能大于100");
            return map;
        }
        // 描述可以为空 但是长度限制
        // 我们还是提供一个默认描述
        if(bot_description == null || bot_description.length() ==0){
            bot_description = "这是一个强大的Bot";
        }
        if(bot_description.length() > 300){
            map.put("error_msg","描述长度不能大于300");
            return map;
        }
        // 代码部分 - content
        if(bot_content == null || bot_content.length() ==0 ){
            map.put("error_msg","内容部分不能为空");
            return map;
        }
        if(bot_content.length() > 20000 ){
            map.put("error_msg","代码长度超过限制范围( 1 - 20000 )");
            return map;
        }

        // 创建时间 + 修改时间
        Date now = new Date();
        // Bot
        BotInfo botInfo = new BotInfo(null, user.getId(), bot_name,bot_description,bot_content,now,now);
        botMapper.insert(botInfo);

        map.put("error_msg","success");
        return  map;
    }
}
