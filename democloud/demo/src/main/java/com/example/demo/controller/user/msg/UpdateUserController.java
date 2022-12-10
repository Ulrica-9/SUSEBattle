package com.example.demo.controller.user.msg;

import com.example.demo.service.user.msg.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @项目名: democloud
 * @文件名: UpdateUserController
 * @作者: LuoTianYu
 * @日期:2022/12/9 23:21
 */
@RestController
public class UpdateUserController {


    @Autowired
    private UpdateUserService updateUserService;



    @PostMapping("/update/user/data/")
    public Map<String,String> update(@RequestParam Map<String,String> data){
        return updateUserService.updateUser(data);
    }
}
