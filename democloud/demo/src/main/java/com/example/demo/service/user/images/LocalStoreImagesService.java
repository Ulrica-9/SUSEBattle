package com.example.demo.service.user.images;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @项目名: democloud
 * @文件名: LocalStoreImages
 * @作者: LuoTianYu
 * @日期:2022/12/7 22:40
 */
public interface LocalStoreImagesService {

    // 设置用户头像文件
    public Map<String,String> setImages(MultipartFile file);
}
