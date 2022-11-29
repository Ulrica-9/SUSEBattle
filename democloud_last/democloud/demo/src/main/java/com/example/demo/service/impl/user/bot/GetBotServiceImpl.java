package com.example.demo.service.impl.user.bot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.mapper.BotMapper;
import com.example.demo.pojo.BotInfo;
import com.example.demo.pojo.User;
import com.example.demo.service.impl.utils.UserDetailsImpl;
import com.example.demo.service.user.bot.GetBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @项目名: demo
 * @文件名: GetBotServiceImpl
 * @作者: LuoTianYu
 * @日期:2022/11/12 15:54
 */
@Service
public class GetBotServiceImpl implements GetBotService {
    @Autowired
    private BotMapper botMapper;
    @Override
    public List<BotInfo> getList() {
        // 把当前用户从本地存储中的Token取出来 对应当前的用户
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();


        QueryWrapper<BotInfo> queryWrapper = new QueryWrapper<>();
        // 这里的user_id 与数据库一致
        queryWrapper.eq("user_id",user.getId());

        return botMapper.selectList(queryWrapper);
    }
}
