package com.example.demo.controller.user.msg;

import com.example.demo.service.impl.images.LocalStoreImagesServiceImpl;
import com.example.demo.service.impl.user.msg.RegisterServiceImpl;
import com.example.demo.service.user.msg.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @项目名: demo
 * @文件名: RegisterController
 * @作者: LuoTianYu
 * @日期:2022/11/7 14:53
 */
@RestController
public class RegisterController {
    @Autowired
    private RegisterService registerService; // 刚刚实现的接口放进来

    @PostMapping("/user/msg/register/")
    public Map<String,String> getRegister(@RequestParam("file") MultipartFile pic,
                                          @RequestParam("username") String username,
                                          @RequestParam("password") String password,
                                          @RequestParam("pwd2") String confirmedPassword,
                                          @RequestParam("email") String email){
        // 这东西暂时不要
        String personal = "";
        String sex = "";
        String school = "";
        return registerService.getRegister(username, password, confirmedPassword,pic,school,email,personal,sex);
    }
}
