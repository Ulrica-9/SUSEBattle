package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123456"));
//        matches(输入 , 原来的密码[存在数据库中的]) -> 匹配
        System.out.println(passwordEncoder.matches("pyxc","$2a$10$CECnVlyMyUZ1KZBWUM7LsuTLYQqkIKe9dQXyx/uu77Ul7qvwkNY.2"));
    }

}
