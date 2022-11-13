package com.kob.backend.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// 自动填充get set等
@NoArgsConstructor
// 无参构造
@AllArgsConstructor
// 有参构造
public class User {
    //    与数据库进行对应的字段
    private Integer id;
    private String username;
    private String password;
}
