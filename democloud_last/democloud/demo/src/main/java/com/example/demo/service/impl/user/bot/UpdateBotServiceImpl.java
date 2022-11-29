package com.example.demo.service.impl.user.bot;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.mapper.BotMapper;
import com.example.demo.pojo.BotInfo;
import com.example.demo.pojo.User;
import com.example.demo.service.impl.utils.UserDetailsImpl;
import com.example.demo.service.user.bot.UpdateBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @项目名: demo
 * @文件名: UpdateBotServiceImpl
 * @作者: LuoTianYu
 * @日期:2022/11/12 15:54
 */
@Service
public class UpdateBotServiceImpl implements UpdateBotService {


    @Autowired
    private  BotMapper botMapper;
    @Override
    public Map<String, String> update(Map<String, String> data) {
        // 把当前用户从本地存储中的Token取出来 对应当前的用户
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        int bot_id = Integer.parseInt(data.get("bot_id"));
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
        if(bot_description == null || bot_description.length() ==0){
            bot_description = "这是一个强大的Bot";
        }
        if(bot_description.length() > 300){
            map.put("error_msg","描述长度不能大于300");
            return map;
        }
        if(bot_content == null || bot_content.length() ==0 ){
            map.put("error_msg","内容部分不能为空");
            return map;
        }
        if(bot_content.length() > 20000 ){
            map.put("error_msg","代码长度超过限制范围( 1 - 20000 )");
            return map;
        }

        BotInfo botInfo = botMapper.selectById(bot_id); // 查询
        if(botInfo== null){
            map.put("error_msg","bot不存在或已被删除");
            return map;
        }
        // 用户是否一致
        if(!botInfo.getUserId().equals(user.getId())){
            map.put("error_msg","没有权限");
            return map;
        }

        // 更新信息
        BotInfo bot = new BotInfo(
                botInfo.getId(),
                user.getId(),
                bot_name,
                bot_description,
                bot_content,
                botInfo.getCreatetime(),
                new Date() // 当前时间 - 修改的时间
        );

        botMapper.updateById(bot);
        map.put("error_msg","修改成功");
        return map;
    }
}
