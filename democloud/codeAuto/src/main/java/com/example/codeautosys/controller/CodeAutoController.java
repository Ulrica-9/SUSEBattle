package com.example.codeautosys.controller;

import com.example.codeautosys.service.CodeAutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @项目名: democloud
 * @文件名: CodeAutoController
 * @作者: LuoTianYu
 * @日期:2022/11/28 16:29
 */
@RestController
public class CodeAutoController {
    @Autowired
    private CodeAutoService codeAutoService;

    @PostMapping("/bot/add/")
    public String addCode(@RequestParam MultiValueMap<String,String> data){

        Integer userId = Integer.parseInt(Objects.requireNonNull(data.getFirst("user_id")));

        String botCode = data.getFirst("bot_code");

        String input = data.getFirst("input");

        return codeAutoService.addCode(userId,botCode,input);
    }
}
