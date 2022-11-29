package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.ranklist.GetRankListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @项目名: democloud
 * @文件名: GetRankListController
 * @作者: LuoTianYu
 * @日期:2022/11/29 20:38
 */
@RestController
public class GetRankListController {


    @Autowired
    private GetRankListService getRankListService;

    @GetMapping ("/ranklist/get/list/")
    private JSONObject getList(@RequestParam Map<String,String> data){
        Integer page  = Integer.parseInt(data.get("page"));

        return getRankListService.getList(page);
    }
}
