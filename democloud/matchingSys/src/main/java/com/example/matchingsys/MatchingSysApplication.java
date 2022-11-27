package com.example.matchingsys;

import com.example.matchingsys.service.impl.MatchingServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @项目名: ${PROJECT_NAME}
 * @文件名: ${NAME}
 * @作者: LuoTianYu
 * @日期:${DATE} ${TIME}
 */
@SpringBootApplication
public class MatchingSysApplication {
    public static void main(String[] args) {
        MatchingServiceImpl.matchingPool.start();  // 启动匹配线程
        SpringApplication.run(MatchingSysApplication.class, args);
    }
}