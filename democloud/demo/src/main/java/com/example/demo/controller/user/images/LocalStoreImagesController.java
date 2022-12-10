package com.example.demo.controller.user.images;

import com.example.demo.service.user.images.LocalStoreImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * @项目名: democloud
 * @文件名: LocalStoreImagesController
 * @作者: LuoTianYu
 * @日期:2022/12/7 22:45
 */


// 进行调用一个文档类
@RestController
public class LocalStoreImagesController {
    // 注入
    @Autowired
    private LocalStoreImagesService localStoreImagesService;


    @PostMapping("/set/images/")
    public Map<String,String> setImages(@RequestParam("file") MultipartFile file){
        if(file.getSize() == 0){
            // 图片为空
            //System.out.println("空");
            return null;
        }

        // 接收前端传过来的数据并传回后端
        //System.out.println("拿到数据" + file);
        return localStoreImagesService.setImages(file);
    }
}
