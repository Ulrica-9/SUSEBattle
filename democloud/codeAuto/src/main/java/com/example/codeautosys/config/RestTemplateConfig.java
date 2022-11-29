package com.example.codeautosys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @项目名: democloud
 * @文件名: RestTemplateConfig
 * @作者: LuoTianYu
 * @日期:2022/11/26 22:42
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
