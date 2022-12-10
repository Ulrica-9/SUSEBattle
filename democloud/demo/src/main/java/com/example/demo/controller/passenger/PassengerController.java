package com.example.demo.controller.passenger;

import com.example.demo.service.user.passenger.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @项目名: democloud
 * @文件名: passengerController
 * @作者: LuoTianYu
 * @日期:2022/12/8 12:31
 */
@RestController
public class PassengerController {

//     注入
    @Autowired
    private PassengerService passengerService;


    // 临时
    @PostMapping("/play/temp/")
    public Map<String,String> getRegister(){

        //System.out.println("测试");
        return passengerService.getRegister();
    }


}
