package com.example.codeautosys;

import com.example.codeautosys.service.impl.CodeAutoServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @项目名: ${PROJECT_NAME}
 * @文件名: ${NAME}
 * @作者: LuoTianYu
 * @日期:${DATE} ${TIME}
 */
@SpringBootApplication
public class codeAutoSys {
    public static void main(String[] args) {

        // 启动线程
        CodeAutoServiceImpl.botPool.start();
        // 执行 springBoot
        SpringApplication.run(codeAutoSys.class,args);
    }
}