package com.example.demo.config;

/**
 * @项目名: demo
 * @文件名: WebSocketConfig
 * @作者: LuoTianYu
 * @日期:2022/11/24 17:31
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
