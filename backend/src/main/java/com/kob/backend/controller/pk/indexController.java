package com.kob.backend.controller.pk;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @version 1.0
 * @Author 作者名
 * @Date 2022/10/27 23:43
 */
// 前后端不分离
@Controller
@RequestMapping("/pk/")
public class indexController {
//    创建页面 - 创建在resources中
    @RequestMapping("index/")
    public String index() {
        return "pk/index.html";
    }
}
