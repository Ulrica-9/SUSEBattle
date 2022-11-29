package com.example.demo.service.ranklist;

import com.alibaba.fastjson.JSONObject;

/**
 * @项目名: democloud
 * @文件名: GetRankListService
 * @作者: LuoTianYu
 * @日期:2022/11/29 20:36
 */
public interface GetRankListService {
   JSONObject getList(Integer page);
}
