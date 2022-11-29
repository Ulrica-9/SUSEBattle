package com.example.demo.service.impl.record;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.RecordMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.Record;
import com.example.demo.pojo.User;
import com.example.demo.service.record.GetRecordListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @项目名: democloud
 * @文件名: GetRecordListImpl
 * @作者: LuoTianYu
 * @日期:2022/11/29 16:46
 */
@Service
public class GetRecordListImpl implements GetRecordListService {


    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private UserMapper userMapper;
    @Override
    public JSONObject getList(Integer page) {

        IPage<Record> recordIPage = new Page<>(page,10); // 每页展示个数
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>(); // 准备排序
        queryWrapper.orderByDesc("id"); // 传id 进行排序 + 可以看全部的人
        List<Record> records = recordMapper.selectPage(recordIPage,queryWrapper).getRecords();
        JSONObject resp = new JSONObject();
        List<JSONObject> items = new LinkedList<>();

        for (Record re :
                records) {
            User user_Pa = userMapper.selectById(re.getAId());
            User user_Pb = userMapper.selectById(re.getBId());
            JSONObject item = new JSONObject();
            item.put("a_photo",user_Pa.getPhoto());
            item.put("a_username",user_Pa.getUsername());
            item.put("b_photo",user_Pb.getPhoto());
            item.put("b_username",user_Pb.getUsername());
            item.put("record",re); //  对局信息存放
            String result = "平局";
            if("A".equals(re.getLoser())) result = user_Pb.getUsername() + " 赢"; // 谁赢
            else if("B".equals(re.getLoser())) result = user_Pb.getUsername() + " 赢";
            item.put("result",result);
            items.add(item);

        }
        // 全部存放
        resp.put("records",items);
        resp.put("records_count",recordMapper.selectCount(null)); // 返回总数
        return resp;
    }
}
