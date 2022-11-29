package com.example.demo.service.record;

import com.alibaba.fastjson.JSONObject;

/**
 * @项目名: democloud
 * @文件名: GetRecordList
 * @作者: LuoTianYu
 * @日期:2022/11/29 16:40
 */

// 获取对局列表
public interface GetRecordListService {
    JSONObject getList(Integer page); //  返回第几页的对局列表
}
