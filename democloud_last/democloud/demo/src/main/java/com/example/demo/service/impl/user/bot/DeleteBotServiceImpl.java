package com.example.demo.service.impl.user.bot;

import com.example.demo.mapper.BotMapper;
import com.example.demo.pojo.BotInfo;
import com.example.demo.pojo.User;
import com.example.demo.service.impl.utils.UserDetailsImpl;
import com.example.demo.service.user.bot.DeleteBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @项目名: demo
 * @文件名: DeleteBotServiceImpl
 * @作者: LuoTianYu
 * @日期:2022/11/12 15:53
 */
@Service
public class DeleteBotServiceImpl implements DeleteBotService {
    @Autowired
    private BotMapper botMapper;


    @Override
    public Map<String, String> del(Map<String, String> data) {
        // 还是要拿到当前user
        // 把当前用户从本地存储中的Token取出来 对应当前的用户
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        // 需要删除的bot
        int bot_id = Integer.parseInt(data.get("bot_id"));
        BotInfo botInfo = botMapper.selectById(bot_id); // 查找删除bot对应的用户

        Map<String,String> map = new HashMap<>();
        if(botInfo== null){

            map.put("error_msg","删除对象不存在");
            return map;
        }
        if(!botInfo.getUserId().equals(user.getId())){
            map.put("error_msg","没有权限");
            return map;
        }

        botMapper.deleteById(bot_id);
        map.put("error_msg", "删除成功");

        return map;
    }
}
