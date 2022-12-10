package com.example.demo.service.impl.ranklist;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.ranklist.GetRankListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @项目名: democloud
 * @文件名: GetRankListServiceImpl
 * @作者: LuoTianYu
 * @日期:2022/11/29 20:37
 */

// 注入
@Service
public class GetRankListServiceImpl implements GetRankListService {



    @Autowired
    private UserMapper userMapper;

    @Override
    public JSONObject getList(Integer page) {
        // 可以查询所有的用户
        IPage<User> userIPage = new Page<>(page,20); // 每页展示个数
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("rating"); // 按照rating 天梯分来进行排序
        List<User> users = userMapper.selectPage(userIPage,queryWrapper).getRecords();
        JSONObject resp = new JSONObject();
        //防止密码泄露  返回之前 密码清空
        for (User user:
             users) {
            user.setPassword(""); // 清空
        }
        resp.put("users",users);
        resp.put("users_count",userMapper.selectCount(null)); // 总数统计

        return resp;
    }
}
