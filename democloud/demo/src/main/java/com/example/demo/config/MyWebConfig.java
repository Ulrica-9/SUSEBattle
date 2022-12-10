package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @项目名: democloud
 * @文件名: MyWebCongfig
 * @作者: LuoTianYu
 * @日期:2022/12/8 22:57
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //WebMvcConfigurer.super.addResourceHandlers(registry);
        //     windows本地用
        registry.addResourceHandler("/img/**").addResourceLocations("file:E:\\web\\img\\");//本地储存地址

        //LINUX服务器用
        // registry.addResourceHandler("/img/**").addResourceLocations("file:/root/opt/img/stuAw");//服务器储存地址
    }
}
