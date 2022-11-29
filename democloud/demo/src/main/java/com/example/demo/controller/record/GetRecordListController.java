package com.example.demo.controller.record;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.record.GetRecordListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @项目名: democloud
 * @文件名: GetReordListImpl
 * @作者: LuoTianYu
 * @日期:2022/11/29 16:44
 */


@RestController
public class GetRecordListController{

    @Autowired
    private GetRecordListService getRecordListService;



    @GetMapping("/get/record/list/")
    JSONObject getList(@RequestParam Map<String,String> data){

        //分页进行展示
        Integer page = Integer.parseInt(data.get("page"));
        return getRecordListService.getList(page);
    }

}
