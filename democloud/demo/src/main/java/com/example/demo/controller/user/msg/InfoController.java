package com.example.demo.controller.user.msg;

import com.example.demo.service.user.msg.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @项目名: demo
 * @文件名: InfoController
 * @作者: LuoTianYu
 * @日期:2022/11/7 1:09
 */
@RestController
public class InfoController {
    @Autowired
    private InfoService infoService;

    // 得到信息就是get了
    @GetMapping("/user/msg/info/")
    public Map<String, String> getinfo() {
        return infoService.getinfo();
    }
}
